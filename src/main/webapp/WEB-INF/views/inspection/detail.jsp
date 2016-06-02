<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html><html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="format-detection" content="telephone=no" />
    <meta http-equiv="Cache-Control" content="must-revalidate,no-cache">
    <meta http-equiv="x-dns-prefetch-control" content="on" />
    <title>检查详情</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/res/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/res/css/css.css">
</head>

<body>
    <section class="container detail">
        <h3></h3>
        
        
    </section>
    <input type="hidden" value="${inspectionCode}" id="inspectionCode">
    <script type="text/javascript" src="${ctx}/res/js/zepto.min.js"></script>
    <script type="text/javascript" src="${ctx}/res/js/public.js"></script>
     <script type="text/javascript" src="${ctx}/res/js/underscore-min.js"></script>
    <script type="text/javascript">
         var inspectionCode = $("#inspectionCode").val();
        $.ajax({
            url: '${ctx}/inspection/queryItemDetail',
            type: 'POST',
            dataType: 'json',
            data: {inspectionCode: inspectionCode},
            success: function(res) {
               var data = res.data;
               $(".container").find("h3").html(data.inspectionName);
               var arr=[ 
               {"name":"适应症","shuxing":data.indication},
               {"name":"禁忌症","shuxing":data.contraindication},
               {"name":"检查作用","shuxing":data.effect},
               {"name":"注意事项","shuxing":data.notes},
               {"name":"临床意义","shuxing":data.clinicalValue}
               ];
             
              var arr_result = _.filter(arr, function(el){ return el.shuxing!==undefined});
               arr_result = _.filter(arr_result, function(el){ return el.shuxing!=="null"});
             for(var i=arr_result.length-1;i>=0;i--){
                var article_html=$("<article class='part'><h5><span>"+(i+1)+"</span>"+arr_result[i].name+"</h5><article class='content'><p>"+arr_result[i].shuxing+"</p></article></article>");
                 $(".container").find("h3").after(article_html); 
             }
               

            }
        })
    </script>
</body>

</html>