# 구해줘 홈즈 Spring 관통

## :pushpin: 메인 화면   
![index](/uploads/3ec827ae2ead539d23b9946aafed47a0/index.PNG)   

## :pushpin: 로그인, 로그아웃
#### 1. 아이디확인
- @GetMapping("/idCheck/{userid}")   
![image](/uploads/781c392875a39a83e7a9e6a57e23b5af/image.png)  
#### 2. 로그인
- @PostMapping("/login")   
![image](/uploads/7745185f73fd93235be5bd0a1b3f909b/image.png)   

## :pushpin: 회원 정보 (member)
#### 1. 회원정보 등록화면
- @PostMapping(value = "/user")   
![image](/uploads/3b6c87b6e635afdf04f6c5d4f7e729a8/image.png)   
- DB   
![image](/uploads/be2034f3f8b46fc7fac4a744d046f192/image.png)
#### 2. 회원정보수정
- @PutMapping("/user")   
![image](/uploads/4af7bcd2552150dc7e81aca9ce441e8b/image.png)   
#### 3. 회원정보 삭제화면
- @DeleteMapping("/user/{userid}")   
![image](/uploads/2ca09eb69c3f640ac504dbe0ee782bb3/image.png)
- DB   
![image](/uploads/5580846be13c07e083489341d7ff379f/image.png)
#### 4. 비밀번호 찾기
- @GetMapping("/user/{userid}")   
![image](/uploads/fc50fc06488be68ca0859daf70623e5c/image.png)   


## :pushpin: 공지사항 (board)
#### 1. 공지사항 목록
- Controller   
![list_code](/uploads/a4b0fb8afbbe5473435e5a7fe181c499/list_code.PNG)
- Mapper   
![image](/uploads/d0300856cbb27d099ce2690757ff719b/image.png)
- Postman Test   
![list_test](/uploads/b0baaff502332c98428ba48eda5a0c11/list_test.PNG)   
   
#### 2. 공지사항 등록
- Controller   
![write_code](/uploads/0821f2a513c393dc254e184636257713/write_code.PNG)
- Mapper   
![image](/uploads/34756a7d91af1194c779033fd4b75c5c/image.png)
- Postman Test   
![write_test](/uploads/983a96371dadc57c2ace0cb5833f17e7/write_test.PNG)
- DB   
![write_db](/uploads/06ac41ae9db9f3b4aa359433680f80f0/write_db.PNG)   
#### 3. 공지사항 수정 이동
- Controller   
![mvmodify_code](/uploads/81619ea81d61ec6fde1423db8f555ac2/mvmodify_code.PNG)
- Postman Test   
![mvmodify_test](/uploads/2a0d7fbd46d230b9e47e298a7ece9991/mvmodify_test.PNG)   
#### 4. 공지사항 삭제
- Controller   
![delete_code](/uploads/fa0f8a009044fc26fbcb2233a584e4f3/delete_code.PNG)
- Mapper   
![image](/uploads/e9433e8d12b287a52de6e270ddcf27d0/image.png)
- Postman Test   
![delete_test](/uploads/9d303af6439a1188d4a306d7bb8c15ba/delete_test.PNG)
- DB   
![delete_db](/uploads/45d0d96588acff977ccfc731babd0760/delete_db.PNG)   
#### 5. 공지사항 수정
- Controller   
![modify_code](/uploads/aacd15301e25d0c4eec006a29994cf7e/modify_code.PNG)
- Mapper   
![image](/uploads/f7295e48166dcdf436611da9076436f1/image.png)
- Postman Test   
![modify_test](/uploads/2fdba5f7e5a69cfa4dd475fa576ddf4a/modify_test.PNG)
- DB   
![modify_db](/uploads/67de019460e1345bac6345948ca16fc1/modify_db.PNG)   

#### 6. 공지사항 검색
- Controller   
![image](/uploads/cb61d58b609cc5b8641046f54af7a9e1/image.png)   
- Mapper   
![image](/uploads/c8b07d75a940e472cfa52dbfe527c5c0/image.png)
- Postman Test   
![image](/uploads/7ccb816f96f6c5a31aafe97fae84ecf1/image.png)   


## :pushpin: 관심지역 주변 정보 (info)
#### 1. 관심 지역 정보
- GetMapping("/view")   
- 선별 진료소
![image](/uploads/dd5631439f483288b01e6bf6bfc1b00f/image.png)
- 상권 정보   
![infoView2](/uploads/7f8b630e1d5bda6ae6c29b67c4eb97a9/infoView2.PNG)
- 숙박 시설   
![infoView3](/uploads/0771cc72b9533a68e22e4911781e99c0/infoView3.PNG)
#### 2. 관심 지역 등록
- @PostMapping("/registArea")   
![image](/uploads/dad2cdb5d013dec7288ea27cdda693aa/image.png)   
- DB
![image](/uploads/5900ce0eb1cf73efdbc611119027919d/image.png)   


## :pushpin: 아파트 (apt)
#### 1. 아파트 등록
- 전   
![apt_resist_before](/uploads/9b5e6dfc4122c7ac21a518bb8173cb67/apt_resist_before.PNG)
- 후   
![apt_resist_after](/uploads/23a802c43d84912a7eb1fcb0a4ae72cc/apt_resist_after.PNG)
- Controller   
![apt_resist_controller](/uploads/8df6ca38f479a7df97d2ab140d5544ac/apt_resist_controller.PNG)
- Mapper   
![apt_resist_mapper](/uploads/0c8258278403306d37bf024d6dface78/apt_resist_mapper.PNG)   
#### 2. 동별 화면
- 실행 조건   
![apt_list_dong_condition](/uploads/b3959aad6c35a0084c7f729694fb4c1a/apt_list_dong_condition.PNG)
- 실행 결과   
![apt_list_dong_result](/uploads/6e4d2f35106e5463627a24de4716223c/apt_list_dong_result.PNG)   
#### 3. 아파트별 검색화면
- 실행 조건   
![apt_list_name_condition](/uploads/6c24d5fa3acf63a7d0387796e67e8e30/apt_list_name_condition.PNG)
- 실행 결과   
![apt_list_name_result](/uploads/d85be58d1b9aa60254489696b1c74816/apt_list_name_result.PNG)   

#### 4. 아파트 검색 코드
- Cotroller   
![apt_list_code](/uploads/7a727c147e20f976b399162778be5c58/apt_list_code.PNG)
- Mapper   
![apt_list_mapper](/uploads/4477852e744b4784d210ec577329468b/apt_list_mapper.PNG)   

## :pushpin: 실거래
#### 1. 거래 삭제
- 요청   
![apt_delete_request](/uploads/39c8aed987b4a4faf2811329f746f2a3/apt_delete_request.PNG)
- 전   
![apt_delete_before](/uploads/552c4372b9853d724d649569b4da78c8/apt_delete_before.PNG)
- 후   
![apt_delete_after](/uploads/7d0270d762c1eb7b1321940ea2c948d8/apt_delete_after.PNG)
- Controller   
![apt_delete_code](/uploads/d65dbda2d820420d62f93ebff171281a/apt_delete_code.PNG)
- Mapper   
![apt_delete_mapper](/uploads/3650c0a6856058b850087357de43a9cd/apt_delete_mapper.PNG)

#### 2. 거래 수정
- 요청   
![apt_update_request](/uploads/ac429f99e4b84e8f8b37eb15b07ab05e/apt_update_request.PNG)
- 전   
![apt_update_before](/uploads/6159f481b991758c9b4fc74dadc5be39/apt_update_before.PNG)
- 후   
![apt_update_after](/uploads/dc38c15847726b29c445b8d85c12a05a/apt_update_after.PNG)
- Controller   
![apt_update_code](/uploads/4e80fdc4ada6a825edbbe41959778323/apt_update_code.PNG)
- Mapper   
![apt_update_mapper](/uploads/6af0c5c2191c4dd634d10f536ed4475f/apt_update_mapper.PNG)
