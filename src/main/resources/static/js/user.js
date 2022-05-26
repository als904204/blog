let index = {
    init:function () {
        $("#btn-join").on("click",() =>{
            this.save();
        });
    },
    save: function () {
        // alert('호출');

        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        // console.log(data);
        $.ajax().done().fail(); // ajax 통신을 이용해 3개의 데이터를 json 으로 변경하여 insert 요청
    }
};

index.init()