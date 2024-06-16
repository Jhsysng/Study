package study.jpashop.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import study.jpashop.service.MemberService;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

}
