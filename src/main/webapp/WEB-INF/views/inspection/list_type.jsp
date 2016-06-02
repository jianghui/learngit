<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html><html>
<head>
    <meta charset="UTF-8">
    <title>检查目录</title>
    <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" type="text/css" href="${ctx}/res/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/res/css/css.css">
</head>

<body>
    <div class="yp_list yp_list2">
        <dl>
            <dt>
                <h3></h3>
                <p></p>
            </dt>
            <dd>
               
            </dd>
        </dl>
        
    </div>
    <input type="hidden" value="${sort}" id="sort">
    <input type="hidden" value="${sortName2}" id="sortName2">
    <input type="hidden" value="${sortName1}" id="sortName1">
    <script type="text/javascript" src="${ctx}/res/js/zepto.min.js"></script>
    <script type="text/javascript" src="${ctx}/res/js/public.js"></script>
    <script type="text/javascript">
        $(function(){
        
                var sort=$("#sort").val(),
                sortName2= $("#sortName2").val(),
                sortName1= $("#sortName1").val();
             $(".yp_list").find("h3").html(sortName1);
             $(".yp_list").find("p").html(sortName2);
              $.ajax({
                url: '${ctx}/inspection/queryItem',
                type: 'POST',
                dataType: 'json',
                data: {inspectionSort:sort,pageNum:1,rows:10},
                success:function(res){
                    var data=res.data;
                    $(".yp_list").find("dd").html("");
                    $.each(data, function(index, item) {
                        var dd_html = $("<a href='${ctx}/view/inspection_detail?inspectionCode=" + item.inspectionCode + "' >" + item.inspectionName+ "</a>");
                        $(".yp_list").find("dd").append(dd_html);
                    })

                }
            })

        })
        
    </script>
</body>

</html>