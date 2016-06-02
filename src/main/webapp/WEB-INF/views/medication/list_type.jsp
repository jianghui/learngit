<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html><html>
<head>
    <meta charset="UTF-8">
    <title>药品目录</title>
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
    <input type="hidden" value="${oneLevelName}" id="oneLevelName">
    <input type="hidden" value="${twoEncodingId}" id="twoEncodingId">
    <input type="hidden" value="${medicationTreeName}" id="medicationTreeName">
    <script type="text/javascript" src="${ctx}/res/js/zepto.min.js"></script>
    <script type="text/javascript" src="${ctx}/res/js/public.js"></script>
    <script type="text/javascript">
        $(function(){
            var twoEncodingId=$("#twoEncodingId").val(),
            oneLevelName= $("#oneLevelName").val();
             $(".yp_list").find("h3").html(oneLevelName);
             $(".yp_list").find("p").html($("#medicationTreeName").val());
            
             $.ajax({
                url: '${ctx}/medication/queryMedicationByTree',
                type: 'POST',
                dataType: 'json',
                data: {encodingId: twoEncodingId,pageNum:1,rows:200},
                success:function(res){
                    var data=res.data;
                    console.log(data);
                    $(".yp_list").find("dd").html("");
                    $.each(data, function(index, item) {
                        var dd_html = $("<a href='${ctx}/view/medication_detail?medicationId=" + item.medicationId + "' >" + item.medicationName + "<p>"+item.originName+"</p></a>");
                        $(".yp_list").find("dd").append(dd_html);
                    })

                }
            })
            
        })
    </script>
</body>

</html>