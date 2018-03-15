var searchdata = "";

function deleteList() {
    //获取所有被选中的记录
    var rows = $("#table").bootstrapTable('getSelections');
    if (rows.length== 0) {
        alert("请先选择要删除的记录!");
        return;
    }
    var ids = '';
    for (var i = 0; i < rows.length; i++) {
        ids += rows[i]['jid'] + ",";
    }
    ids = ids.substring(0, ids.length - 1);
    deleteIds(ids);
}

//单个删除
function deleteUserById(id) {
    deleteUser(id);
}


//删除
function deleteIds(ids) {
    var msg = "您真的确定要删除吗？";
    if (confirm(msg) == true) {
        $.ajax({
            url: "/admin/job/delete",
            type: "post",
            data: {
                jids: ids
            },
            success: function (data) {
              //  alert(data.msg);
                //重新加载记录
                //重新加载数据
                $("#table").bootstrapTable('refresh', {url: '/admin/job'});
                table();
            }
        });
    }
}
function table() {
    var url = "/admin/job/search/" + searchdata;
    $('#table').bootstrapTable('destroy');
    $('#table').bootstrapTable({
        url: url,//修改
        method: 'get',
        toolbar: '#toolbar',                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
//            queryParams:queryParams,//传递参数（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
//            contentType: "application/x-www-form-urlencoded",
        strictSearch: true,
        //showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        height: 700,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "no",                     //每一行的唯一标识，一般为主键列
        showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        columns: [{
            checkbox: true,
            title: '选择',
        }, {
            field: 'jid',
            title: 'JID',
            visible: false
        }, {
            field: 'enterprise.eid',
            title: 'EID',
            visible: false
        }, {
            field: 'jname',//命名需与后台一致
            title: '职位名称'
        }, {
            field: 'enterprise.ename',
            title: '公司名'
        }, {
            field: 'workPlace',
            title: '工作地点'
        }, {
            field: 'minSalary',
            title: '最低工资(千)'
        }, {
            field: 'maxSalary',
            title: '最高工资(千)'
        }]

    });

}

//    function searchinfo(index) {
//        $("#position").val(position[index]["position"]);//修改
//        $("#number").val(position[index]["number"]);
//        $("#requirement").val(position[index]["requirement"]);
//        $("#company").val(position[index]["company"]);
//        $("#department").val(position[index]["department"]);
//        $("#Pintroduction").val(position[index]["Pintroduction"]);
//        $("#location").val(position[index]["location"]);
//        $("#Cintroduction").val(position[index]["Cintroduction"]);
//        $("#mymodel").modal();
//    }




$(document).ready(function () {
    $("#search").click(function () {
        searchdata = $("#searchdata").val();
        table();



    });

})

