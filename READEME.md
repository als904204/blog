# Spring Security + Thymeleaf Blog 


## 06.03 Security 로그인으로 수정
- ### 기존 로그인 코드 삭제
- UserApiController
```java
public class UserApiController {
    @PostMapping("/api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
        log.info("login() 호출");
        User principal = userService.login(user); // principal 접근 주체

        if (principal != null) {
            session.setAttribute("principal", principal);
            log.info("session={}", session.getAttribute("principal"));
        }

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
```
- UserService
```java
public class UserService{
 @Transactional(readOnly=true)
    public User login(User user){
        User nullUser= userRepository.findByUsername(user.getUsername()).orElseThrow(() ->{
                    throw new IllegalArgumentException("해당 ID 유저를 찾을 수 없습니다");
          }
        );
        return userRepository.login(user.getUsername(), user.getPassword());
    }
}
```
- UserRepository
```java
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2",nativeQuery = true)
    User login(String username, String password);
}
```
- loginForm.html 수정 (name 추가)
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
<head>
    <div th:replace="/layout/header.html :: fragment-header"></div>
</head>
<body>

<div th:replace="/layout/navbar.html :: fragment-nav"></div>
<div class="container">
    <form action="#" method="post">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
        </div>

        <div class="form-group form-check">
            <label class="form-check-label">
                <input name="remember" class="form-check-input" type="checkbox"> Remember me
            </label>
        </div>
        <button id="btn-login" class="btn btn-primary">Login</button>
    </form>

</div>

<footer th:replace="/layout/footer.html :: fragment-footer"></footer>


</body>
</html>

<!-- user.js 삭제 -->
<!-- <script src="/js/user.js"></script> -->
```

- user.js 수정(주소 변경, 로그인 삭제)
```javascript
let index = {
    init:function () {
        $("#btn-join").on("click",() =>{
            this.save();
        });

    },


    save: function () {
        // alert('호출');

        // html 에 있는 id 들 값 바인드
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        // console.log(data);
        // ajax 통신을 이용해 3개의 데이터를 json 으로 변경하여 insert 요청
        // ajax 호출 시 default 가 비동기 호출
        $.ajax({
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data), // javascript 의 data 를 JSON 로 변환하여 JAVA 로 전달
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤 type 인지
            dataType: "json" // 응답된 데이터가 json 이라면 javascript 오브젝트로 받음
        }).done(function (res) {
            console.log(res);
            alert("successful!");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },


};

index.init()
```

----
# 추가할 기능
- 관리자 페이지
- 댓글 답변
