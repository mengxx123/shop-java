
function ajaxDelete(text, url, onSuccess) {

	eui.confirm(text, function(){
		$.ajax({ 
			url: url, 
			type: 'get', 
			cache: false,
			dataType: 'json', 
			success: function(obj) {
				if (obj.code === 0) {
					onSuccess();
				} else {
					//eui.alert("删除失败，" + obj.data); // TODO 这里有问题，显示不出来
					alert("删除失败，" + obj.data);
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrow) {
				eui.alert("系统出错");
				//alert(XMLHttpRequest.status);
	            //alert(XMLHttpRequest.readyState);
	            //alert(textStatus);
			}
		});
    });
	
	
}