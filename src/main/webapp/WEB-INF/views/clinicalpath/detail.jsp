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
    <title>临床路径详情</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/res/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/res/css/css.css">
</head>

<body>
    <section class="container detail">
        <header class="lclj-head">
            <h4></h4>
            <p>标准住院日 <span></span></p>
        </header>
        <div class="yp_list lclj">
            <ul class="yp_list_tap">
                <li class="cur">标准住院流程</li>
                <li>临床路径表单</li>
            </ul>
            <section class="lclj-lc active">
             <div id="biaoshi2"></div>
              
                <footer class="btn">
                    <a id="goTop" href="#" class="icon-btn"><img src="${ctx}/res/img/go_top.png" alt=""></a>
                    <a id="menu" href="javascript:void(0);" class="icon-btn"><img src="${ctx}/res/img/menu.png" alt=""></a>
                </footer>
            </section>
            <section class="lclj-lc">
                <div class="yp_list_con jy_list_con">
                    <dl class="cur">
                        <dd>
                           
                        </dd>
                    </dl>
                </div>
            </section>
            <section class="nav-layer" id="nav-layer">
                <article class="left"></article>
                <ul class="nav">
                 <div id="biaoshi"></div>
                </ul>
            </section>
        </div>
    </section>
     <input type="hidden" value="${clinicalPathId}" id="clinicalPathId">
      <input type="hidden" value="${clinicalPathName}" id="clinicalPathName">
       <input type="hidden" value="${specialistName}" id="specialistName">
       
    <form action="${ctx}/view/clinicalPath/formDetail" method="post" id="jvForm">
    	<input type="hidden" value="" name="pointName" id="pointName">
    	<input type="hidden" value="" name="treatmentWorks" id="treatmentWorks">
    	<input type="hidden" value="" name="focusAdvice" id="focusAdvice">
    	<input type="hidden" value="" name="nursingWork" id="nursingWork">
    	<input type="hidden" value="" name="clinicalPathName" id="clinicalPathName2">
    	<input type="hidden" value="" name="specialistName" id="specialistName2">
    	<input type="hidden" value="" name="standardDays" id="standardDays">
    </form>
    <script type="text/javascript" src="${ctx}/res/js/zepto.min.js"></script>
    <script type="text/javascript" src="${ctx}/res/js/touch.js"></script>
    <script type="text/javascript" src="${ctx}/res/js/public.js"></script>

    <script type="text/javascript" src="${ctx}/res/js/underscore-min.js"></script>
    <script type="text/javascript">
    $(function() {
        stoploading();
    });
    </script>
</body>

</html>
