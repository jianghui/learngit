<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>疾病百科</title>
    <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" type="text/css" href="${ctx}/res/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/res/css/css.css">
</head>

<body>
    <div class="yp_search" id="search">
        <input type="text" placeholder="请输入疾病名称搜索" id="myInput" />
        <button class="confirm" disabled="disabled">搜索</button>
        <button class="cancel">取消</button>
    </div>
    <div class="jb_list removeTextNodes list">
        <div class="left">
        </div>
        <div class="right">
            
        </div>
    </div>
     <div class="search_result">
       
    </div>
    <script type="text/javascript" src="${ctx}/res/js/zepto.min.js"></script>
    <script type="text/javascript" src="${ctx}/res/js/public.js"></script>
    <script type="text/javascript">
    $(function() {
        // $("#more").on('click', function(event) {
        //     event.preventDefault();

        // })
        $("#myInput").val("");
        $.ajax({
                url: '${ctx}/disease/querySpecialist',
                type: 'POST',
                dataType: 'json',
                // data: {param1: 'value1'},
                success: function(res) {
                    var data = res.data;
                    $.each(data, function(index, item) {
                        var left_html = $("<a data-specialistId='" + item.specialistId + "'>" + item.specialistName + "</a>");
                        $(".jb_list").find(".left").append(left_html);
                    });
                    $(".jb_list").find(".left").find("a").eq(0).addClass('cur');
                    //初始化
                     var specialistId_one = $(".left").find("a").eq(0).attr("data-specialistId"); 
                     $.ajax({
                            url: '${ctx}/disease/queryDiseaseEntitiesBySpecialist', 
                            type: 'POST',
                            dataType: 'json',
                             data: {specialistId:specialistId_one,pageNum:1,rows:200},
                            success: function(res) {
                               
                                var data = res.data;

                                $.each(data, function(index, item) {

                                    var right_html = $("<a href='${ctx}/view/disease_type?diseaseEntityId=" + item.diseaseEntityId + "&diseaseEntityName=" + item.diseaseEntityName + "'>" + item.diseaseEntityName + "</a>");

                                    $(".left").next().append(right_html);
                                });


                            }
                        });
                    //点击回去二级列表
                    $(".left").on('click', 'a', function(event) {
                        event.preventDefault();
                        $(this).parent(".left").next().html("");
                        //一级的encodingid（用于ajax参数）和名称（用于传给a链接然后在二级栏目显示） 
                        var specialistId = $(this).attr("data-specialistId"); //url的encodingid用这个变量
                        var that = $(this);
                        $.ajax({
                            url: '${ctx}/disease/queryDiseaseEntitiesBySpecialist', 
                            type: 'POST',
                            dataType: 'json',
                            data: {specialistId:specialistId,pageNum:1,rows:200},
                            success: function(res) {
                                var data = res.data;

                                $.each(data, function(index, item) {

                                    var right_html = $("<a href='${ctx}/view/disease_detail?diseaseEntityId=" + item.diseaseEntityId + "&diseaseEntityName=" + item.diseaseEntityName + "'>" + item.diseaseEntityName + "</a>");

                                    that.parent(".left").next().append(right_html);
                                });


                            }
                        });
                        
                            $(this).addClass('cur').siblings('a').removeClass('cur');
                            var index = $(".jb_list a.cur").index();
                            $(".jb_list .right").eq(index).removeClass('none').siblings('div').addClass('none');
                      

                      

                    });

                }

            });
         $("#search").on('click', '.confirm', function(event) {
            event.preventDefault();
            $(this).hide().next(".cancel").show();
            var input_val= $("#myInput").val();
            
              $.ajax({
                url: '${ctx}/disease/queryDiseaseEntitiesByKey',
                type: 'POST',
                dataType: 'json',
                data: {keyValue: input_val,pageNum:1,rows:200},
                success:function(res){

                    var data=res.data;
                    $(".search_result").html("");
                    $.each(data, function(index, item) {
                        var search_result_html =$("<a href='${ctx}/view/disease_detail?diseaseEntityId=" + item.diseaseEntityId + "&diseaseEntityName="+ item.diseaseEntityName +"' >" + item.diseaseEntityName + "</a>");
                        $(".search_result").append(search_result_html);
                    })

                }
            })
        });
            //去掉inline-block的间距
        $('.removeTextNodes').contents().filter(function() {
            return this.nodeType === 3;
        }).remove();



    })
    </script>
</body>

</html>