package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // 2. 기존에 ServiceImpl에서 구현체에 의존했던것을 주석처리
    // private MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // 3. 여기서 MemberServiceImpl 생성자를 만들어서 객체 생성시 의존성 주입받도록 만들기
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트용 코드
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}