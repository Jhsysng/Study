package hello.login.web.member;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepoository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberRepoository memberRepoository;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member")Member member) {
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("member")Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/addMemberForm";
        }

        memberRepoository.save(member);
        return "redirect:/";
    }
}
