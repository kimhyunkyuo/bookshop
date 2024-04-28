# 쇼핑몰 프로젝트
  스프링부트와 JPA, security를 이용하여 구현한 책 온라인 쇼핑몰 프로젝트입니다.
 
## 개요
- 프로젝트 명 : bookshop
- 개발기간 : 2024.03.19 ~ 2024.04.18
- 개발인원 :  1명
- 주요기능
   - SpringSecurity 회원가입 및 로그인
   - Oauth2로그인 (구글 소셜로그인 구현) 
   - spring-boot-starter-mail(라이브러리 사용)
## 사용기술
### #Back-End
- java  17
- SpringBoot
- Spring Security
- Spring Data JPA
- OAuth2.0

### #Front-End
- JavaScript
- BootStrap
- Thymeleaf
  
#### BuildTool
- Maven
#### DataBase
- MySQL
#### API
- Kakao 주소 API
- Google 로그인 API

## 개발환경
- IntelliJ
- DBeaver
- Github

## 요구사항 분석
 ### 회원가입 페이지
- 이메일 형식 패턴 적용해 확인 및 비동기 방식으로 이메일 인증확인
- 한 개의 칸이라도 공백 혹은 빈칸이 있는지 확인하고 있다면, "OOO는 필수 입력 값입니다."의 메시지 보여주기
- 카카오 주소 API 사용

### 로그인 페이지
- 아이디와 비밀번호가 일치하지 않을 시 "아이디 또는 비밀번호가 일치하지 않습니다. "의 메시지를 보여주기
- 구글 소셜 로그인 구현
- 비 로그인시 (상품 화면 및 상세보기만 가능)
## ERD 다이어그램

![스크린샷 2024-04-24 193229](https://github.com/kimhyunkyuo/bookshop/assets/131740127/2c3fc983-1de6-42d5-8424-36ff01d7a72b)


## 실행영상
[![Video Label](http://img.youtube.com/vi/qrJZxsNvKAE/0.jpg)](https://youtu.be/qrJZxsNvKAE?t=0s)



