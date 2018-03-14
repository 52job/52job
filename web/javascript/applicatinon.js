
<!--职位申请-->
function selectJob(){
    var rid= $("#selectedResume").val();
    var jid =localStorage.getItem("jid");
    alert("enter")
    $.ajax({
        url: '/collection/addApplication',//修改
        type: 'post',
        //dataType: 'json',
        //contentType : 'application/json',
        data:"rid="+rid+'&jid='+jid,
        success: success,
        error: success
    })
}

function success(data) {
    var jid = localStorage.getItem(jid);
    // deleteUserById(jid);
    alert(data.msg);
    document.location.reload();
}


<!--得到简历-->
function getResume() {
    $.ajax({
        url: '/collection/getResume',//修改
        type: 'post',
        //dataType: 'json',
        //contentType : 'application/json',
        success: success,
        error: success
    })

    function success(data) {
        var obj = JSON.parse(data);
        for(var i=0;i<obj.length;i++){
            // alert(obj[i].rid);
            var option = document.createElement("option");
            //转换DOM对象为JQ对象,好用JQ里面提供的方法 给option的value赋值
            $(option).val(obj[i].rid);
            //给option的text赋值,这就是你点开下拉框能够看到的东西
            $(option).text('简历id:'+obj[i].rid);
            //获取select 下拉框对象,并将option添加进select
            $('#selectedResume').append(option);
        }
    }
}


function app(jid){
    localStorage.setItem("jid",jid);
    $("#setJob").modal();
}



<!--得到简历信息-->
function getResumeById(){
    var rid= $("#selectedResume").val();
    $.ajax({
        url: '/resume/searchRid/'+rid,//修改
        type: 'post',
        dataType: 'json',
        contentType : 'application/json',
        success: show,
        error: show
    })
}

function show(data) {
    var val = eval(data);
    //$("#imageId").attr("src","xxxx.jpg");
    //$('#home_keleyi_com').attr('href','http://keleyi.com');
    alert(val.name);
    $("#name").val(val.name);
    $("#born").val(val.birthday);
    $("#sex").val(val.sex);
    $("#work_date").val(val.startWorkTime);
    $("#tel").val(val.tel);
    $("#email").val(val.email);
    $("#school").val(val.graduationUniversity);
    $("#graduate-time").val(val.graduationTime);
    $("#education").val(val.highestEducation);
    if(val.isPublic==1){
        $("#resume_state").val("公开");
    }else{
        $("#resume_state").val("不公开");
    }
    $("#job_state").val(val.jobState);
    $("#address").val(val.address);
    $("#major").val(val.major);
}
