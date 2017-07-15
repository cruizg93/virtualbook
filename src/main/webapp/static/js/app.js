$(document).ready(function(){
	scaleSiteContainer();
	$("input[type=text]").focusout(function(){
		$(this).val($(this).val().toUpperCase());
	});
});

function scaleSiteContainer() {
    var navbarHeight = ($('.navbar-fixed-top').height()+5) + 'px',
        height = $(window).height(),
        unitHeight = parseInt(height) + 'px';

    $('#site-content').css({
      'margin-top': navbarHeight,
      'height': unitHeight
    });  
}