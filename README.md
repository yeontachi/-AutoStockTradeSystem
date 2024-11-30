[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/TV9kFNJz)
# Project Proposal

0. **팀명 및 팀원**
   - 팀명과 팀원 구성을 간략히 기술

1. **프로젝트 개요** (~300자)
   - 프로젝트의 목적과 목표를 간결히 기술
   - 예: "이 프로젝트는 학생 관리 시스템을 Java로 구현해 클래스 설계와 상속 개념을 실습하는 것을 목표로 한다."

2. **주요 기능** (~500자)
   - 팀이 개발할 주요 기능 목록을 나열
   - 예: 회원 등록, 정보 수정, 삭제 기능 등

3. **역할 분담** (~300자)
   - 팀원별 역할과 담당할 모듈을 간단히 설명

4. **개발 일정** (~300자)
   - 마일스톤을 정의하고, 기능 구현 및 테스트 일정을 제시

5. **사용 기술 및 도구** (~200자)
   - 개발에 사용할 기술이나 도구, Java 기능/문법 등을 작성

6. **기대 효과 및 마무리** (~200자)
   - 이 프로젝트를 통해 배우게 될 점과 프로젝트의 완성 목표를 정리

```
team-project-2
├─ .git
│  ├─ COMMIT_EDITMSG
│  ├─ config
│  ├─ description
│  ├─ FETCH_HEAD
│  ├─ HEAD
│  ├─ hooks
│  │  ├─ applypatch-msg.sample
│  │  ├─ commit-msg.sample
│  │  ├─ fsmonitor-watchman.sample
│  │  ├─ post-update.sample
│  │  ├─ pre-applypatch.sample
│  │  ├─ pre-commit.sample
│  │  ├─ pre-merge-commit.sample
│  │  ├─ pre-push.sample
│  │  ├─ pre-rebase.sample
│  │  ├─ pre-receive.sample
│  │  ├─ prepare-commit-msg.sample
│  │  ├─ push-to-checkout.sample
│  │  ├─ sendemail-validate.sample
│  │  └─ update.sample
│  ├─ index
│  ├─ info
│  │  └─ exclude
│  ├─ logs
│  │  ├─ HEAD
│  │  └─ refs
│  │     ├─ heads
│  │     │  ├─ main
│  │     │  └─ seonhu
│  │     └─ remotes
│  │        └─ origin
│  │           ├─ HEAD
│  │           ├─ main
│  │           └─ seonhu
│  ├─ objects
│  │  ├─ 0c
│  │  │  └─ 9835aa30d993d94baa43c1245aed258edcd364
│  │  ├─ 11
│  │  │  └─ 4c4f1ec42cd3221a1ecda624c0d2974299bf94
│  │  ├─ 1e
│  │  │  └─ 12390cc9b566d9424941d27ae836ac02fceee6
│  │  ├─ 3e
│  │  │  └─ 5a87189e90cb58cbdd8a0beaee66ad6c5376de
│  │  ├─ 3f
│  │  │  └─ 58352404898ff1dfab5c00434332e92be71fd7
│  │  ├─ 40
│  │  │  └─ 6458e3a539f7040b3945fc322fccf46d5617b0
│  │  ├─ 49
│  │  │  └─ faafdeee07f295b167e562562b8f766a060b65
│  │  ├─ 50
│  │  │  └─ de9a76f0438a5efaab83948e9f9d0b0b76edb4
│  │  ├─ 54
│  │  │  └─ 975b67e790840d91dc8d12ee4d19b29c2a5a2b
│  │  ├─ 5d
│  │  │  └─ 71bc120873cf1bcdf43be9eb0669f0aa505a03
│  │  ├─ 60
│  │  │  └─ 5afab55056f2f886338bb74e5a0afb513ebf1f
│  │  ├─ 6e
│  │  │  └─ a7a6380dc920652580184b3a075a715b9840f8
│  │  ├─ 75
│  │  │  └─ d2bedbd50f7782b782b83773552fdbbecd1a2e
│  │  ├─ 9a
│  │  │  └─ 6abde788814369e77acd9ce2d007f171c3f3c4
│  │  ├─ c1
│  │  │  └─ 09b2d3245daa60e3bc38ee5e7c553d40eff6ed
│  │  ├─ ce
│  │  │  └─ a8782f531a3bdef584e5152865045cc71a0270
│  │  ├─ d5
│  │  │  └─ 6b2b5db6568c7ecfca2fe1c799338ef11f3b11
│  │  ├─ fe
│  │  │  └─ aac273179c4e8947a9638d3e11898c820c3c07
│  │  ├─ info
│  │  └─ pack
│  │     ├─ pack-2b41fd1d5dba175c08381155ab7489c9d4185a55.idx
│  │     ├─ pack-2b41fd1d5dba175c08381155ab7489c9d4185a55.pack
│  │     └─ pack-2b41fd1d5dba175c08381155ab7489c9d4185a55.rev
│  ├─ ORIG_HEAD
│  ├─ packed-refs
│  └─ refs
│     ├─ heads
│     │  ├─ main
│     │  └─ seonhu
│     ├─ remotes
│     │  └─ origin
│     │     ├─ HEAD
│     │     ├─ main
│     │     └─ seonhu
│     └─ tags
├─ .gitattributes
├─ .gitignore
├─ app
│  └─ src
│     ├─ main
│     │  ├─ java
│     │  │  └─ org
│     │  │     └─ project
│     │  │        ├─ AbstractCompany.java
│     │  │        ├─ AutoHandler.java
│     │  │        ├─ ConcreteCompany.java
│     │  │        ├─ User.java
│     │  │        ├─ Main.java
│     │  │        ├─ Manager.java
│     │  │        └─ Trade.java
│     │  └─ resources
│     └─ test
│        ├─ java
│        │  └─ org
│        │     └─ project
│        │        └─ MainTest.java
│        └─ resources
├─ gradle
│  ├─ libs.versions.toml
│  └─ wrapper
│     ├─ gradle-wrapper.jar
│     └─ gradle-wrapper.properties
├─ gradle.properties
├─ gradlew
├─ gradlew.bat
├─ Proposal.md
└─ README.md

```