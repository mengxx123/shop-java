// JavaScript Document
/** 
 * Cookie绫�
 */ 

function Cookie() { 
	this.isNet = true;
	this.strCookie = '';
	
	this.getDocumentCookie = function() {
		if (this.isNet) {
			return document.cookie;
		} else {
			return this.strCookie;
		}
	};
	
	this.setDocumentCookie = function(strCookie) {
		if (this.isNet) {
			document.cookie = strCookie;
		} else {
			this.strCookie = strCookie;
		}	
	};
	
	/** 
	 * @desc 璁剧疆Cookie 
	 * @return void 
	 */ 
	this.setCookie = function(name, value, hours) { 
		var expire = ""; 
		if (hours != null) { 
			expire = new Date((new Date()).getTime() + hours * 3600000); 
			expire = "; expires=" + expire.toGMTString(); 
		} 
		this.setDocumentCookie(escape(name) + "=" + escape(value) + expire);
	};

	/** 
	 * @desc 璇诲彇Cookie 
	 * @return String 
	 */
	this.getCookie = function(name) { 
		var cookieValue = ""; 
		var search = escape(name) + "="; 
		var strCookie = this.getDocumentCookie();
		if (strCookie.length > 0) {  
			offset = strCookie.indexOf(search); 
			if (offset != -1) {  
				offset += search.length; 
				end = strCookie.indexOf(";", offset); 
				if (end == -1) {
					end = strCookie.length; 
				}
				cookieValue = unescape(strCookie.substring(offset, end)); 
			}
		}
		return cookieValue;
	};
} 

/**
 * 璐墿杞︾被
 */
function Car(name) {

	if (!window.clientInformation.cookieEnabled) { 
		alert('浣犵殑娴忚鍣ㄤ笉鏀寔Cookie鏃犳硶浣跨敤姝�璐墿杞�绯荤粺'); 
		return false; 
	}
	
	// 鍐呴儴鍙橀噺
	
	this.carName = name; 
	this.time = 24 * 30; // 璐墿杞︾殑鏈夋晥鏃堕棿(30澶� 
	this.goodses = new Array(); 
	this.cookie = new Cookie();

	this.refreshCookie = function() {
		
	};
	
	this.addGoods = function(id, name, money) {
		var strCookie = this.cookie.getCookie(this.carName);
		if (strCookie == '') {
			strCookie += id + '#' + name + '#' + money;
		} else {
			strCookie += '|' + id + '#' + name + '#' + money;
		}
		this.cookie.setCookie(this.carName, strCookie, this.time);
	};
	
	this.deleteGoods = function(id) {
		var strCookie = this.cookie.getCookie(this.carName);
		var list = strCookie.split('|');
		strCookie = '';
		for (var i = 0; i < list.length; i++) {
			var ss = list[i].split('#');
			if (ss[0] != id) {
				strCookie += list[i];
			}
		}
		
		this.cookie.setCookie(this.carName, strCookie, this.time);
	};
	
	this.getTotalMoney = function() {
		var strCookie = this.cookie.getCookie(this.carName);
		var list = strCookie.split('|');
		var totalMoney = 0;
		for (var i = 0; i < list.length; i++) {
			var ss = list[i].split('#');
			totalMoney += parseInt(ss[2]);
		}	
		return totalMoney;
	};
	
	/** 
	 * @desc 鍒犻櫎姝よ喘鐗╄溅 
	 * @return void 
	 */ 
	this.deleteCar = function() { 
		this.cookie.setCookie(this.carName, '', 0);
	};
}
	