package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

    @AfterEach //각 메소드 테스트 끝나고 실행
    public void afterEach() {
        memoryMemberRepository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");
        memoryMemberRepository.save(member);

        Member result = memoryMemberRepository.findById(member.getId()).get();

        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");

        memoryMemberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memoryMemberRepository.save(member2);


        Member result = memoryMemberRepository.findByName("spring1").get();

        Assertions.assertThat(member1).isEqualTo(result);


    }

    @Test
    public void findAll() {

        Member member1 = new Member();
        member1.setName("spring1");
        memoryMemberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memoryMemberRepository.save(member2);

        Member member3 = new Member();
        member3.setName("spring3");
        memoryMemberRepository.save(member3);

        Member member4 = new Member();
        member4.setName("spring4");
        memoryMemberRepository.save(member4);

        List<Member> resultList = memoryMemberRepository.findAll();

        Assertions.assertThat(resultList.size()).isEqualTo(4);
    }
}
