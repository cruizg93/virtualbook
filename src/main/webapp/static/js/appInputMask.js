$(document).ready(function(){
	/**
	 * Only text with spaces
	 */
	$('.appText').keypress(function (e) {
        var regex = new RegExp('^[a-zA-Z\\-\\s]+$');
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
            return true;
        }else{
	        e.preventDefault();
	        return false;
        }
    });
	
	/**
	 * Only numbers - no spaces
	 */
	$('.appNumber').keypress(function (e) {
        var regex = new RegExp('^[0-9]+$');
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
            return true;
        }else{
	        e.preventDefault();
	        return false;
        }
    });
	
	/**
	 * Alphanumeric with spaces and symbols [& @ /]
	 */
	$('.appTextNumber').keypress(function (e) {
        var regex = new RegExp('^[a-zA-Z0-9\\-\\s&@/]+$');
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
            return true;
        }else{
	        e.preventDefault();
	        return false;
        }
    });
	
	/**
	 * Alphanumberic with symbol @ following Alphanumeric and next . with Alphanumberic again
	 */
	$('.appEmail').keypress(function (e) {
        var regex = new RegExp('^[a-zA-Z0-9@a-zA-Z0-9.a-zA-Z]+$');
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
            return true;
        }else{
	        e.preventDefault();
	        return false;
        }
    });
	
	/**
	 * Alphanumeric wiht , four times
	 */
	$('.appAddress').keypress(function (e) {
        var regex = new RegExp('^[a-zA-Z0-9\\-\\s,a-zA-Z0-9\\-\\s,a-zA-Z0-9\\-\\s,a-zA-Z0-9\\-\\s]+$');
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
            return true;
        }else{
	        e.preventDefault();
	        return false;
        }
    });
	
	/**
	 * Mask for USA phone number format
	 */
	$(".appUSPhoneNumber").mask('(000) 000-0000');
});