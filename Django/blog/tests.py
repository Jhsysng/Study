from django.test import TestCase, Client
from bs4 import BeautifulSoup
from .models import Post, Category, Tag, Comment
from django.contrib.auth.models import User
class TestView(TestCase):
    def setUp(self):
        self.client=Client()
        self.user_trump = User.objects.create_user(username='trump', password='1q2w3e4r!')
        self.user_obama = User.objects.create_user(username='obama',password='somepassword')
        self.user_obama.is_staff=True
        self.user_obama.save()


        self.category_programming= Category.objects.create(name='programming',slug='programming')
        self.category_music = Category.objects.create(name='music',slug='music')

        self.tag_python_kor=Tag.objects.create(name='파이썬 공부',slug='파이썬-공부')
        self.tag_python=Tag.objects.create(name='python',slug='python')
        self.tag_hello=Tag.objects.create(name='hello',slug='hello')

        self.post_001=Post.objects.create(
            title='1st',
            content='Hello',
            category=self.category_programming,
            author=self.user_trump
        )
        self.post_001.tags.add(self.tag_hello)

        self.post_002=Post.objects.create(
            title='2nd',
            content='Hello World, We are the World',
            category=self.category_music,
            author=self.user_obama
        )

        self.post_003=Post.objects.create(
            title='3rd',
            content='word',
            author=self.user_obama
        )
        self.post_003.tags.add(self.tag_python_kor)
        self.post_003.tags.add(self.tag_python)

        self.comment_001=Comment.objects.create(
            post=self.post_001,
            author=self.user_obama,
            content='1st comment'
        )
    def test_update_post(self):
        update_post_url=f'/blog/update_post/{self.post_003.pk}/'

        #not login
        response=self.client.get(update_post_url)
        self.assertNotEqual(response.status_code, 200)

        #login but not writter
        self.assertNotEqual(self.post_003.author, self.user_trump)
        self.client.login(
            username=self.user_trump.username,
            password='1q2w3e4r!'
        )

        response=self.client.get(update_post_url)
        self.assertEqual(response.status_code,403)

        #wirtter login
        self.client.login(
            username=self.post_003.author.username,
            password='somepassword'
        )

        response=self.client.get(update_post_url)
        self.assertEqual(response.status_code,200)
        soup=BeautifulSoup(response.content,'html.parser')

        self.assertEqual('Edit Post - Blog', soup.title.text)
        main_area=soup.find('div', id='main-area')
        self.assertIn('Edit Post', main_area.text)

        tag_str_input=main_area.find('input',id='id_tags_str')
        self.assertTrue(tag_str_input)
        self.assertIn('파이썬 공부;python',tag_str_input.attrs['value'])
        response=self.client.post(
            update_post_url,
            {
                'title':'3rd edited',
                'content':'hello world edit',
                'category':self.category_music.pk,
                'tags_str':'파이썬 공부; 한글_태그, some_tag'
            },
            follow=True
        )
        soup = BeautifulSoup(response.content,'html.parser')
        main_area=soup.find('div',id='main-area')
        self.assertIn('3rd edited',main_area.text)
        self.assertIn('hello world edit',main_area.text)
        self.assertIn(self.category_music.name,main_area.text)
        self.assertIn('파이썬 공부',main_area.text)
        self.assertIn('한글_태그',main_area.text)
        self.assertIn('some_tag',main_area.text)
        self.assertNotIn('python',main_area.text)


    def test_create_post(self):
        response=self.client.get('/blog/create_post/')
        self.assertNotEqual(response.status_code,200)

        #staff가 아닌 trump 로그인
        self.client.login(username='trump',password='1q2w3e4r!')
        response=self.client.get('/blog/create_post/')
        self.assertNotEqual(response.status_code,200)

        #staff
        self.client.login(username='obama',password='somepassword')

        response=self.client.get('/blog/create_post/')
        self.assertEqual(response.status_code,200)
        soup=BeautifulSoup(response.content,'html.parser')

        self.assertEqual('Create Post - Blog',soup.title.text)
        main_area=soup.find('div',id='main-area')
        self.assertIn('Create New Post',main_area.text)

        tag_str_input=main_area.find('input',id='id_tags_str')
        self.assertTrue(tag_str_input)

        self.client.post(
            '/blog/create_post/',
            {
                'title':'post form',
                'content':"post form page",
                'tags_str':'new_tag; 한글태그, python'
            }
        )
        last_post=Post.objects.last()
        self.assertEqual(last_post.title,"post form")
        self.assertEqual(last_post.author.username,'obama')

        self.assertEqual(last_post.tags.count(),3)
        self.assertTrue(Tag.objects.get(name='new_tag'))
        self.assertTrue(Tag.objects.get(name='한글태그'))
        self.assertEqual(Tag.objects.count(),5)
    def test_tag_page(self):
        response=self.client.get(self.tag_hello.get_absolute_url())
        self.assertEqual(response.status_code,200)
        soup=BeautifulSoup(response.content,'html.parser')

        self.navbar_test(soup)
        self.category_card_test(soup)
        self.assertIn(self.tag_hello.name,soup.h1.text)

        main_area=soup.find('div',id='main-area')
        self.assertIn(self.tag_hello.name,main_area.text)
        self.assertIn(self.post_001.title, main_area.text)
        self.assertNotIn(self.post_002.title, main_area.text)
        self.assertNotIn(self.post_003.title, main_area.text)

    def test_category_page(self):
        response = self.client.get(self.category_programming.get_absolute_url())
        self.assertEqual(response.status_code,200)

        soup=BeautifulSoup(response.content, 'html.parser')
        self.navbar_test(soup)
        self.category_card_test(soup)

        self.assertIn(self.category_programming.name, soup.h1.text)

        main_area=soup.find('div',id='main-area')
        self.assertIn(self.category_programming.name,main_area.text)
        self.assertIn(self.post_001.title,main_area.text)
        self.assertNotIn(self.post_002.title,main_area.text)
        self.assertNotIn(self.post_003.title, main_area.text)
    def category_card_test(self,soup):
        categories_card=soup.find('div',id='categories-card')
        self.assertIn('Categories',categories_card.text)
        self.assertIn(f'{self.category_programming.name} ({self.category_programming.post_set.count()})',categories_card.text)
        self.assertIn(f'{self.category_music.name} ({self.category_music.post_set.count()})',categories_card.text)

        self.assertIn(f'미분류(1)',categories_card.text)

    def navbar_test(self,soup):
        navbar=soup.nav
        self.assertIn('Blog',navbar.text)
        self.assertIn('About me',navbar.text)

        logo_btn=navbar.find('a', text='Django_index')
        self.assertEqual(logo_btn.attrs['href'],'/')

        home_btn=navbar.find('a',text='Home')
        self.assertEqual(home_btn.attrs['href'],'/')

        blog_btn=navbar.find('a', text='Blog')
        self.assertEqual(blog_btn.attrs['href'],'/blog/')

        about_me_btn= navbar.find('a', text='About me')
        self.assertEqual(about_me_btn.attrs['href'],'/about_me/')
    def test_post_list(self):
        self.assertEqual(Post.objects.count(),3)

        response=self.client.get('/blog/')
        self.assertEqual(response.status_code,200)
        soup=BeautifulSoup(response.content,'html.parser')

        self.navbar_test(soup)
        self.category_card_test(soup)

        main_area=soup.find('div',id='main-area')
        self.assertNotIn('아직 게시물이없습니다.',main_area.text)

        post_001_card=main_area.find('div',id='post-1')
        self.assertIn(self.post_001.title,post_001_card.text)
        self.assertIn(self.post_001.category.name,post_001_card.text)
        self.assertIn(self.post_001.author.username.upper(),post_001_card.text)
        self.assertIn(self.tag_hello.name,post_001_card.text)
        self.assertNotIn(self.tag_python.name,post_001_card.text)
        self.assertNotIn(self.tag_python_kor.name,post_001_card.text)

        post_002_card=main_area.find('div',id='post-2')
        self.assertIn(self.post_002.title,post_002_card.text)
        self.assertIn(self.post_002.category.name,post_002_card.text)
        self.assertIn(self.post_002.author.username.upper(),post_002_card.text)
        self.assertNotIn(self.tag_hello.name,post_002_card.text)
        self.assertNotIn(self.tag_python.name,post_002_card.text)
        self.assertNotIn(self.tag_python_kor.name, post_002_card.text)

        post_003_card=main_area.find('div',id='post-3')
        self.assertIn('미분류', post_003_card.text)
        self.assertIn(self.post_003.title,post_003_card.text)
        self.assertIn(self.post_003.author.username.upper(),post_003_card.text)
        self.assertNotIn(self.tag_hello.name,post_003_card.text)
        self.assertIn(self.tag_python.name,post_003_card.text)
        self.assertIn(self.tag_python_kor.name,post_003_card.text)


        self.assertIn(self.user_trump.username.upper(),main_area.text)
        self.assertIn(self.user_obama.username.upper(),main_area.text)

        #notpost
        Post.objects.all().delete()
        self.assertEqual(Post.objects.count(),0)
        response=self.client.get('/blog/')
        soup=BeautifulSoup(response.content,'html.parser')

        main_area=soup.find('div',id='main-area')
        self.assertIn('아직 게시물이 없습니다.',main_area.text)

    def test_post_detail(self):

        self.assertEqual(self.post_001.get_absolute_url(),'/blog/1/')

        response=self.client.get(self.post_001.get_absolute_url())
        self.assertEqual(response.status_code,200)
        soup=BeautifulSoup(response.content,'html.parser')

        self.navbar_test(soup)

        self.category_card_test(soup)

        main_area=soup.find('div',id='main-area')
        post_area=soup.find('div',id='post-area')
        self.assertIn(self.post_001.title,post_area.text)

        self.assertIn(self.category_programming.name,post_area.text)

        self.assertIn(self.post_001.content,post_area.text)

        self.assertIn(self.tag_hello.name, post_area.text)
        self.assertNotIn(self.tag_python.name,post_area.text)
        self.assertNotIn(self.tag_python_kor.name,post_area.text)

        #comment area
        comment_area=soup.find('div',id='comment-area')
        comment_001_area=comment_area.find('div',id='comment-1')
        self.assertIn(self.comment_001.author.username, comment_001_area.text)
        self.assertIn(self.comment_001.content,comment_001_area.text)

    def test_comment_form(self):
        self.assertEqual(Comment.objects.count(), 1)
        self.assertEqual(self.post_001.comment_set.count(), 1)

        # not login
        response = self.client.get(self.post_001.get_absolute_url())
        self.assertEqual(response.status_code, 200)
        soup = BeautifulSoup(response.content, 'html.parser')

        comment_area = soup.find('div', id='comment-area')
        self.assertIn('Log in and leave comment', comment_area.text)
        self.assertFalse(comment_area.find('form', id='comment-form'))

        # login
        self.client.login(username='obama', password='somepassword')
        response = self.client.get(self.post_001.get_absolute_url())
        self.assertEqual(response.status_code, 200)
        soup = BeautifulSoup(response.content, 'html.parser')

        comment_area = soup.find('div', id='comment-area')
        self.assertNotIn('Log in and leave a comment', comment_area.text)

        comment_form = comment_area.find('form', id='comment-form')
        self.assertTrue(comment_form.find('textarea',id='id_content'))
        response = self.client.post(
            self.post_001.get_absolute_url() + 'new_comment/',
            {
                'content': 'obama\'s comment',
            },
            follow=True
        )
        self.assertEqual(response.status_code, 200)
        self.assertEqual(Comment.objects.count(), 2)
        self.assertEqual(self.post_001.comment_set.count(), 2)

        new_comment = Comment.objects.last()

        soup = BeautifulSoup(response.content, 'html.parser')
        self.assertIn(new_comment.post.title, soup.title.text)

        comment_area = soup.find('div', id='comment-area')
        new_comment_div = comment_area.find('div', id=f'comment-{new_comment.pk}')
        self.assertIn('obama', new_comment_div.text)
        self.assertIn('obama\'s comment', new_comment_div.text)

    def test_comment_update(self):
        comment_by_trump = Comment.objects.create(
            post=self.post_001,
            author=self.user_trump,
            content="trump's comment"
        )

        response = self.client.get(self.post_001.get_absolute_url())
        self.assertEqual(response.status_code, 200)
        soup = BeautifulSoup(response.content, 'html.parser')

        comment_area = soup.find('div', id='comment-area')
        self.assertFalse(comment_area.find('a', id='comment-1-update-btn'))
        self.assertFalse(comment_area.find('a', id='comment-2-update-btn'))

        # login
        self.client.login(username='obama',password='somepassword')
        response = self.client.get(self.post_001.get_absolute_url())
        self.assertEqual(response.status_code, 200)
        soup = BeautifulSoup(response.content, 'html.parser')

        comment_area = soup.find('div', id='comment-area')
        self.assertFalse(comment_area.find('a', id='comment-2-update-btn'))
        comment_001_update_btn = comment_area.find('a', id='comment-1-update-btn')
        self.assertIn('edit', comment_001_update_btn.text)
        self.assertEqual(comment_001_update_btn.attrs['href'], '/blog/update_comment/1/')


        response = self.client.get('/blog/update_comment/1/')
        self.assertEqual(response.status_code, 200)

        soup = BeautifulSoup(response.content, 'html.parser')
        #update_comment_form = soup.find('form', id='comment-from')
        #content_textarea = update_comment_form.find('textarea', id='id_content')
        #self.assertIn(self.comment_001.content, content_textarea.text)


        self.assertEqual('Edit Comment - Blog', soup.title.text)


        response = self.client.post(
            f'/blog/update_comment/{self.comment_001.pk}/',
            {
                'content': "obama's comment edit",
            },
            follow=True
        )

        self.assertEqual(response.status_code, 200)
        soup = BeautifulSoup(response.content, 'html.parser')
        comment_001_div = soup.find('div', id='comment-1')
        self.assertIn("obama's comment edit", comment_001_div.text)
        self.assertIn('Updated:', comment_001_div.text)

    def test_delete_comment(self):
        comment_by_trump=Comment.objects.create(
            post=self.post_001,
            author=self.user_trump,
            content="trump's comment"
        )

        self.assertEqual(Comment.objects.count(),2)
        self.assertEqual(self.post_001.comment_set.count(),2)

        #not login
        response=self.client.get(self.post_001.get_absolute_url())
        self.assertEqual(response.status_code,200)
        soup=BeautifulSoup(response.content,'html.parser')

        comment_area=soup.find('div',id='comment-area')
        self.assertFalse(comment_area.find('a',id='comment-1-delete-btn'))
        self.assertFalse(comment_area.find('a',id='comment-2-delete-btn'))

        #trump login
        self.client.login(username='trump',password='1q2w3e4r!')
        response=self.client.get(self.post_001.get_absolute_url())
        self.assertEqual(response.status_code,200)

        soup=BeautifulSoup(response.content,'html.parser')

        comment_area=soup.find('div',id='comment-area')
        self.assertFalse(comment_area.find('a',id='comment-1-delete-btn'))
        comment_002_delete_modal_btn=comment_area.find('a',id='comment-2-delete-modal-btn')
        self.assertIn('delete',comment_002_delete_modal_btn.text)
        self.assertEqual(comment_002_delete_modal_btn.attrs['data-bs-target'],'#deleteCommentModal-2')

        delete_comment_modal_002=soup.find('div',id='deleteCommentModal-2')
        self.assertIn('Are You Sure?',delete_comment_modal_002.text)
        really_delete_btn_002=delete_comment_modal_002.find('a')
        self.assertIn('Delete',really_delete_btn_002.text)
        self.assertEqual(
            really_delete_btn_002.attrs['href'],
            "/blog/delete_comment/2/"
        )

        response=self.client.get('/blog/delete_comment/2/',follow=True)
        #self.assertEqual(response.status_code,200)
        #soup.BeautifulSoup(response.content,'html.parser')
        #self.assertIn(self.post_001.title,soup.title.text)
        #comment_area=soup.find('div',id='comment-area')
        #self.assertNotIn("trump's comment",comment_area.text)

        self.assertEqual(Comment.objects.count(),1)
        self.assertEqual(self.post_001.comment_set.count(),1)

    def test_search(self):
        post_about_python=Post.objects.create(
            title = "python",
            content = "hello world",
            author = self.user_trump
        )

        response=self.client.get('/blog/search/python/')
        self.assertEqual(response.status_code, 200)
        soup=BeautifulSoup(response.content,'html.parser')

        main_area=soup.find('div',id='main-area')

        self.assertIn('Search:python(2)',main_area.text)
        self.assertNotIn(self.post_001.title,main_area.text)
        self.assertNotIn(self.post_002.title,main_area.text)
        self.assertIn(self.post_003.title,main_area.text)
        self.assertIn(post_about_python.title,main_area.text)



# Create your tests here.
