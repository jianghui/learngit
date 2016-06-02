/**
 * 
 * @authors Your Name (you@example.org)
 * @date    2016-04-27 16:25:49
 * @version $Id$
 */

Zepto(function($) {
    // 回到顶部
    window.onscroll = function() {
        var move = $(window).scrollTop();
        if (move > 100) {
            $("#goTop").css('visibility', 'visible');
        } else {
            $("#goTop").css('visibility', 'hidden');
        }
    }
    $("#goTop").tap(function() {
        $(window).scrollTop(0);
    });
    
    // 菜单按钮
    $("#menu").tap(function() {
        $("#nav-layer").show();
    });
    $("#nav-layer .left").tap(function(event) {
        $("#nav-layer").hide();
    });
    $("#nav-layer a").tap(function() {
        event.preventDefault();
        var oPart=$(this).data('href');
        var oScrollerHeight=$(oPart).offset().top;
        $(window).scrollTop(oScrollerHeight);
        $(this).parent().addClass('active').siblings('li').removeClass('active');
    });
})
