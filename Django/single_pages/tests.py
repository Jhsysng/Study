from django.test import TestCase, Client
from django.contrib.auth.models import User
from bs4 import BeautifulSoup
from blog.models import Post

class TestView(TestCase):
    def setUp(self):
        self.client = Client()
        self.user_trump = User.objects.create_user(username='trump', password='somepassword')

    def test_landing(self):
        post_001 = Post.objects.create(
            title="first",
            content="first post",
            author=self.user_trump
        )

        post_002=Post.objects.create(
            title="second",
            content="second post",
            author=self.user_trump
        )

        post_003=Post.objects.create(
            title="3rd",
            content="3rd post",
            author=self.user_trump
        )

        post_004=Post.objects.create(
            title='4th',
            content='4th post',
            author=self.user_trump
        )

        response=self.client.get('')
        self.assertEqual(response.status_code,200)
        soup=BeautifulSoup(response.content,'html.parser')

        body=soup.body
        self.assertNotIn(post_001.title,body.text)
        self.assertIn(post_002.title,body.text)
        self.assertIn(post_003.title,body.text)
        self.assertIn(post_004.title, body.text)

# Create your tests here.
