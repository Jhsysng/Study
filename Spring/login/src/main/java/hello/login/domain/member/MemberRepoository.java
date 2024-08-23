package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class MemberRepoository {

        private static Map<Long, Member> store = new HashMap<>();
        private static long sequence = 0L;

        public Member save(Member member) {
            member.setId(++sequence);
            log.info("save: member={}", member);
            store.put(member.getId(), member);
            return member;
        }

        public Member findById(Long memberId) {
            return store.get(memberId);
        }

        public Optional<Member> findByLoginId(String loginId) {
            return findAll().stream()
                    .filter(m -> m.getLoginId().equals(loginId))
                    .findFirst();
        }

        public List<Member> findAll() {
            return List.copyOf(store.values());
        }

        public void clearStore() {
            store.clear();
        }
}
