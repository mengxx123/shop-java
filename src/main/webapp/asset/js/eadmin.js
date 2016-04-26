$(function() {
	$('#menu').metisMenu();
	$('#menu-minimalize').on('click', function(){
		//alert('ok');
		$('#menu').animate(
			{width: '100px'},
			1000
		);

		$('#content').css('margin-left', '50px');
	});


	$('#sidebar').slimScroll({
		width: $('#sidebar').width(),
		height: $(window).height()
	});

	$("[data-toggle='tooltip']").tooltip();
	$('[data-toggle="popover"]').popover();
});