서버와 클라이언트

브라우저의 역할
서버에 요청하고 받아와서 그려주는 역할

서버는 API라는 밖에다 요청을 받을 수 있는 창구를 열어놓는다.

HTML : 뼈대
CSS : 꾸미기
javascript : 움직이게 하는 것 (동작과 관련된 역할을 한다.)

html:5 단축키를 사용하면 기본적인 뼈대가 나온다.
<>안에 들어간 것을 태그라고 한다.
<!--주석내용--> : 주석 사용할 때 이렇게 사용

자주 쓰이는 CSS
연습할것들 
h1, h5, background-image,background-size,background-position
color,width,height,border-radius,margin,padding

margin 과 padding
여백의 값을 넣으면 시계방향으로 돈다 
margin = 바깥 여백 
padding = 내 안쪽 여백
div 박스

전체 선택 후 컨트롤 k f = 줄마춤


모든 내용을 가운데에 맞출려면 상하좌우 여백이 동일하다는 뜻이다.
먼저는 background색상을 먹여서 영역을 확인 후 맞춘다.
꽉차있을 시 사이즈를 줄여서 사용
margin: auto 상하좌우 전부 동일하게 여백을 마춰준다

스타일 설정시 *를 사용하면 모든 class에 적용

1일차 숙제 완료

만약 컴포넌트가 하나 있을 때는 class를 지정하지 않고 바로 불러도 된다 ex) body{}

폰트적용

구글폰트에서 가져와 
타이틀 밑에 Use on the web 이하내용을 붙이고
style 안에 적용할 컴포넌트에 CSS rules to specify families이하 내용을 적용

@media screen and (max-width: 760px) { 760이하일 경우에만 읽힌다.

}

모바일 화 시킬 때는 구글 개발자도구에서 모바일 버전을 이용하여 크기를 조절한다.

간단한 자바스크립트 맛보기

자바스크립트 또한 함수를 이용
class 뒤에 함수를 붙여줘서 사용