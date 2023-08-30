# 병원 검색 서비스
병원 정보 서비스 공공API(https://www.data.go.kr/data/15001698/openapi.do) 를 활용한 병원 검색 서비스

## 🐬Skills
- **Language** : Kotlin
- **Design Pattern** : MVVM
- **Android Skills**
    - Modularization
    - Retrofit2 + Tikxml
    - *AAC(Android Architecture Components)* : ViewModel, DataBinding
    - StateFlow, Coroutine

## 🐟Clean Architecture with 3 main modules
Android Developers 모듈화 가이드 기반 (https://developer.android.com/topic/modularization?hl=ko)
- **Data** : API(Retrofit2), Room DB tasks
- **Domain** : Business Logic, Models
- **Feature** : UI Logic(with ViewModel), UI components

## 🐳Release Notes
### [v.1.0.0]
- 검색 창을 통해 전국의 병원을 검색할 수 있어요.
- 병원 주소, 홈페이지, 전화 번호 등 병원 상세 정보를 확인할 수 있어요.
- 앱에서 바로 전화를 걸고, 병원 홈페이지를 볼 수 있어요.

* * *
### *Reference*
* 공공API: https://www.data.go.kr/data/15001698/openapi.do
* 모듈화 가이드: https://developer.android.com/topic/modularization?hl=ko
* <a href="https://www.flaticon.com/kr/free-icons/" title="취소 아이콘">취소 아이콘  제작자: abdanbagus - Flaticon</a>
* <a href="https://www.flaticon.com/kr/free-icons/-" title="검색 데이터 아이콘">검색 데이터 아이콘  제작자: abdanbagus - Flaticon</a>
* <a href="https://www.flaticon.com/kr/free-icons/-" title="왼쪽 화살표 아이콘">왼쪽 화살표 아이콘  제작자: abdanbagus - Flaticon</a>
