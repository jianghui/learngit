<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>检查百科</title>
    <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" type="text/css" href="${ctx}/res/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/res/css/css.css">
</head>

<body>
    <div class="yp_search" id="search">
        <input type="text" placeholder="请输入检查项目名称搜索" id="myInput" />
        <button class="confirm" disabled="disabled">搜索</button>
        <button class="cancel">取消</button>
    </div>
    <div class="yp_list list">
        <div class="yp_list_con jy_list_con">
            
        </div>
    </div>
    <div class="search_result">
    </div>
    <script type="text/javascript" src="${ctx}/res/js/zepto.min.js"></script>
    <script type="text/javascript" src="${ctx}/res/js/public.js"></script>
    <script type="text/javascript">
    $(function() {
          $("#myInput").val("");
        $.ajax({
            url: '${ctx}/inspection/querySort',
            type: 'POST',
            dataType: 'json',
            success: function(res) {
                var data = res.data;
                $.each(data, function(index, item) {
                    var dl_html = $("<dl><dt><h3>" + item.sortName + "</h3></dt><dd></dd></dl>");
                    $(".yp_list").find(".yp_list_con").append(dl_html);
                    $.each(item.childs, function(index2, item2) {
                        var dd_html = $("<a href='${ctx}/view/inspection_type?sortName1=" + item.sortName + "&sortName2=" + item2.sortName + "&sort=" + item2["sort"] + "'>" + item2.sortName + "</a>");
                        $(".yp_list").find(".yp_list_con").find("dl").eq(index).find("dd").append(dd_html);
                    })

                });
                $(".yp_list_con dl").on('click', 'dt', function(event) {
                    event.preventDefault();
                    $(this).parent("dl").toggleClass('cur');
                });
            }
        })

        $("#search").on('click', '.confirm', function(event) {
            event.preventDefault();
            $(this).hide().next(".cancel").show();
            var input_val = $("#myInput").val();

            $.ajax({
                url: '${ctx}/inspection/queryItemByKey',
                type: 'POST',
                dataType: 'json',
                data: {keyValue: input_val,pageNum:1,rows:200},
                success: function(res) {

                    var data = res.data;
                    $(".search_result").html("");
                    $.each(data, function(index, item) {
                        var search_result_html = $("<a href='${ctx}/view/inspection_detail?inspectionCode=" + item.inspectionCode + "' >" + item.inspectionName + "</a>");
                        $(".search_result").append(search_result_html);
                    })

                }
            })
        });

    })
    </script>
</body>

</html>