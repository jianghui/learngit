<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>健康百科</title>
    <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" type="text/css" href="${ctx }/res/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${ctx }/res/css/css.css">
</head>

<body>
    <div class="yp_search" id="search">
        <input type="text" placeholder="如：疾病，药品，检验，检查 " id="myInput" />
        <button class="confirm" disabled="disabled">搜索</button>
        <button class="cancel">取消</button>
    </div>
    <div class="index_list list">
        <ul>
        <a href="${ctx }/view/medication">
            <li class="yp">
	            <div>
	            	<h2>药品百科</h2>
	            	<p>提供全面的药品信息</p>
                </div>
            </li>
        </a>
        <a href="${ctx }/view/disease">
            <li class="jb">
            	<div>
	            	<h2>疾病百科</h2>
	            	<p>完善的疾病信息库</p>
	            </div>
            </li>
            </a>
            <a href="${ctx }/view/inspection">
            <li class="jc">
            	<div>
	            	<h2>检查百科</h2>
	            	<p>b超，放射等检查项目解读</p>
             </div>
            </li>
              </a>
              <a href="${ctx }/view/examine">
            <li class="jy">
            	<div>
	            	<h2>检验百科</h2>
	            	<p>实验室检验项目解读</p>
                </div>
            </li>
             </a>
             <a href="${ctx }/view/clinicalpath">
            <li class="lc">
            	<div>
	            	<h2>临床路径</h2>
	            	<p>疾病的标准化治疗模式与治疗程序</p>
	            </div>
            </li>
            </a>
        </ul>
    </div>
     <div class="search_result">
        
    </div>
    <script type="text/javascript" src="${ctx }/res/js/zepto.min.js"></script>
    <script type="text/javascript" src="${ctx }/res/js/public.js"></script>
    <script type="text/javascript" src="${ctx }/res/js/underscore-min.js"></script>
    <script type="text/javascript">
        $(function(){
             $("#myInput").val("");
            var html = document.querySelector('html');
            var rem = $(window).width()/70;
            html.style.fontSize = rem + "px";

            $("#search").on('click', '.confirm', function(event) {
            event.preventDefault();
            $(this).hide().next(".cancel").show();
            var input_val= $("#myInput").val();
            
              $.ajax({
                url: '${ctx }/search',
                type: 'POST',
                dataType: 'json',
                data: {keyValue:input_val,pageNum:1,rows:10},
                success:function(res){

                    var data=res.data,
                    medications=data.medications,//药品
                    diseaseEntitys=data.diseaseEntitys,//疾病
                    examines=data.examines,//检验报告
                    examineApplys=data.examineApplys,//检验申请
                    inspections=data.inspections;//检查
                    $(".search_result").html("");
                    if(medications.length>0){
                        $(".search_result").html("<h3>药品</h3>");
                        $.each(medications, function(index, item) {
                            var search_result_html =$("<a href='${ctx}/view/medication_detail?medicationId=" + item.medicationId + "&medicationName="+ item.medicationName +"&commodityName="+ item.commodityName+"&originName="+item.originName+"' >" + item.medicationName + "<p>"+item.originName+"</p></a>");
                            $(".search_result").append(search_result_html);
                        })
                    }   
                    if(diseaseEntitys.length>0){
                    $(".search_result").append("<h3>疾病</h3>");
                    $.each(diseaseEntitys, function(index, item) {
                        var search_result_html =$("<a href='${ctx}/view/disease_detail?diseaseEntityId=" + item.diseaseEntityId + "&diseaseEntityName=" + item.diseaseEntityName + "'>" + item.diseaseEntityName + "</a>");
                        $(".search_result").append(search_result_html);
                    })
                   }
                   if(examines.length>0){
                    $(".search_result").append("<h3>检验报告</h3>");
                    $.each(examines, function(index, item) {
                        var search_result_html =$("<a href='${ctx}/view/examine_detail?examineName=" + item.examineName + "&examineCode=" + item.examineCode + "&type=1'>" + item.examineName + "</a>");
                        $(".search_result").append(search_result_html);
                    })
                   }
                   if(examineApplys.length>0){
                    $(".search_result").append("<h3>检验申请</h3>");
                    $.each(examineApplys, function(index, item) {
                        var search_result_html =$("<a href='${ctx}/view/examine_detail?examineName=" + item.examineName + "&examineCode=" + item.examineCode + "&type=2'>" + item.examineName + "</a>");
                        $(".search_result").append(search_result_html);
                    })
                   }
                   if(inspections.length>0){
                    $(".search_result").append("<h3>检查</h3>");
                    $.each(inspections, function(index, item) {
                        var search_result_html =$("<a href='${ctx}/view/inspection_detail?inspectionCode=" + item.inspectionCode + "&inspectionName="+ item.inspectionName +"' >" + item.inspectionName+ "</a>");
                        $(".search_result").append(search_result_html);
                    })
                   }
              }
            })
        });

            
        })

    </script>
</body>

</html>