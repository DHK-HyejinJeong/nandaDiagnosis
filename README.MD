## 2019  09 14 project start

## project name: NANDA diagnosis project
## with nursing major junior JH, C

> 2019 09 15

    - firebase performance -> install complete
    - ux design -> 0.5 h
    - admob setting -> later

> 2019 09 16

    - 아이콘 만들기
    - 대략적인 내용있는 프레그 먼트 만들 것
        - 요구사항대로 
    - 다크 모드 지원을 할 것
    - 내용 구성을 어떻게 할지 알아보기
    
> 2019 09 17     
    
    - 아이콘 만들기 -> complete
        - 힘들면 보색으로 아이콘 구성
    
    - 프로그램 구조 정하기
        - activity 구조 -> complete
        - fragment 구조 -> complete
    
    - viewpager 사용하기 -> complete
        
    - 데이터베이스 구성
        - 스키마 작업 
        - server DB
        - 안드로이드 내부 DB > Jetpack library (Room)
        - SQLite
        
> 2019 09 18
    
    - 데이터베이스 구성
        - 데이터베이스 파일을 만들 것
        - 첫 시작시 데이터를 받아올 수 있도록 할 것
            - 와이파이 연결시에만 받아올 것
         
    - 도움말 액티비티/프레그먼트 짤 것 -> 리스트뷰
        - 도움말
        - 문의하기
        - 오픈소스 라이선스
        
> 2019 09 19

    - 데이터베이스 구축
        - 생각보다 간단하다.
        - 규칙은 필요할 듯 (띄어쓰기를 하지 말아야 하나?)
        - 엔티티 -> 위험 요인(reason), 진단 요인(diagnosis)
        - 엑셀 -> db 만들기
    
    - 도움말 문의 액티비티 구현      
    
    - 검색 기능 -> 위험 요인을 검색을 하면 진단 요인이 나오는 것


## 2019 09 20

    - 테스트 코드 배워서 적용하기

    - 도움말 문의 액티비티 만들기

    - 검색 가능 구현 (categroy frgamnet search 환경으로 변경)ㄴ
        - 첫 시작시 데이터베이스 받아오는 코드 작성
        - searchView -> database 의 연결을 하기 -> SQLite
