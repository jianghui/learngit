<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>检验百科</title>
    <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" type="text/css" href="${ctx}/res/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/res/css/css.css">
</head>

<body>
    <div class="yp_search" id="search">
    <input type="text"  placeholder="请输入检验项目名称搜索"  id="myInput" /><button class="confirm" disabled="disabled">搜索</button><button class="cancel">取消</button>
    
    </div>
    <div class="yp_list list">
        <ul class="yp_list_tap">
            <li class="cur">检验申请项目</li>
            <li>检验报告项目</li>
        </ul>
        <div class="yp_list_con jy_list_con">
        </div>
        <div class="yp_list_con jy_list_con none ">
        </div>
    </div>
    <div class="search_result">
     
    </div>
    <script type="text/javascript" src="${ctx}/res/js/zepto.min.js"></script>
    <script type="text/javascript" src="${ctx}/res/js/public.js"></script>
     <script type="text/javascript" src="${ctx}/res/js/underscore-min.js"></script>
    <script type="text/javascript">
        $(function(){
             $("#myInput").val("");
            $.ajax({
            url: '${ctx}/examine/queryExamine',
            type: 'POST',
            dataType: 'json',
           
            success: function(res) {
                var data = res.data;
                var type1 = _.where(data, {
                        'type': 1
                    }),
                    type2 = _.where(data, {
                        'type': 2
                    });
                  
                $.each(type1, function(index, item) {
                    var dl_html = $("<dl><dt><h3>" + item.sortName + "</h3></dt><dd></dd></dl>");
                    $(".yp_list").find(".yp_list_con").eq(1).append(dl_html);
                    $.each(item.childs, function(index2, item2) {
                        var dd_html = $("<a href='${ctx}/view/examine_type?sortName1=" + item.sortName + "&sortName2=" + item2.sortName + "&sort=" + item2["sort"] + "&type=" + item2["type"] + "'>" + item2.sortName + "</a>");
                        $(".yp_list").find(".yp_list_con").eq(1).find("dl").eq(index).find("dd").append(dd_html);
                    })

                }); 

                 $.each(type2, function(index, item) {
                    var dl_html = $("<dl><dt><h3>" + item.sortName + "</h3></dt><dd></dd></dl>");
                    $(".yp_list").find(".yp_list_con").eq(0).append(dl_html);
                    $.each(item.childs, function(index2, item2) {
                        var dd_html = $("<a href='${ctx}/view/examine_type?sortName1=" + item.sortName + "&sortName2=" + item2.sortName + "&sort=" + item2["sort"] + "&type=" + item2["type"] + "'>" + item2.sortName + "</a>");
                        $(".yp_list").find(".yp_list_con").eq(0).find("dl").eq(index).find("dd").append(dd_html);
                    })

                });  
              
              
                    //点击回去二级列表
                $(".yp_list_con dl").on('click', 'dt', function(event) {
                    event.preventDefault();
                    $(this).parent("dl").toggleClass('cur');


                });

            }

        })

        $(".yp_list_tap").on('click', 'li', function(event) {
            event.preventDefault();
            $(this).addClass('cur').siblings('li').removeClass('cur');
            var index = $(".yp_list_tap li.cur").index();
            $(".yp_list_con").eq(index).removeClass('none').siblings('div').addClass('none');
        });

        
        $("#search").on('click', '.confirm', function(event) {
            event.preventDefault();
            $(this).hide().next(".cancel").show();
            var input_val= $("#myInput").val();
            
              $.ajax({
                url: '${ctx}/examine/queryReportByKey',
                type: 'POST',
                dataType: 'json',
                data: {keyValue: input_val,pageNum:1,rows:200},
                 success: function(res) {

                    var data = res.data;
                    $(".search_result").html("");
                    var type1 = _.where(data, {
                            'type': 1
                        }),
                        type2 = _.where(data, {
                            'type': 2
                        });
                    if(type1.length>0){
                    $.each(type1, function(index, item) {
                        var h3_html=$("<h3>检验报告项目</h3>");
                         $(".search_result").append(search_result_html);
                        var search_result_html = $("<a href='${ctx}/view/examine_detail?examineCode=" + item.examineCode + "&examineName=" + item.examineName + "&type=" + item["type"] + "' >" + item.examineName + "</a>");
                        $(".search_result").append(search_result_html);
                    })
                };
                 if(type2.length>0){
                    $.each(type2, function(index, item) {
                        var h3_html=$("<h3>检验申请项目</h3>");
                         $(".search_result").append(search_result_html);
                        var search_result_html = $("<a href='${ctx}/view/examine_detail?examineCode=" + item.examineCode + "&inspectionName=" + item.examineName + "&type=" + item["type"] + "' >" + item.examineName + "</a>");
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
