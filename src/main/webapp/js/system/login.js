


var login = {

    /**
     * 登录相关类
     */
    login:function(formId){
        var url_ = 'sys/user/login.htm';
        var data_ = $('#' + formId).serializeArray();
        var object = JSON.parse(ajaxs.sendAjax('post' , url_ , data_));
        if(object.resultCode == 0){
            window.location.href='lp/index.htm';
        }else{
            alert(object.resultMessage);
        }
    },

    /**
     * 退出相关类
     */
    logout:function(){

    },

    /**
     * 记住密码 TODO 可以用Js的方式保存密码
     */
    keepPassword:function(){

    }


}


