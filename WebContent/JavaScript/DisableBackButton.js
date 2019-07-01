/**
 * this method is used for disable the right click button
 */
function disableRightClick(event) {
	if (event.button == 2) {
		document.oncontextmenu = new Function("return false");

	}

}
/**
 * this method is used for disable the refresh button
 */
function disableRefresh(event) {
	var key_f5 = 116;
	if (key_f5 == event.keyCode
			|| (event.ctrlKey && (event.keyCode == 78 || event.keyCode == 82))) {
		if (event.preventDefault) {
			event.preventDefault();
			return false;
		} else {
			event.keyCode = 0;
			event.returnValue = false;
		}
	}
}
/**
 * this method is used for disable the back button
 */
function changeHashOnLoad() {
	window.location.href += "#";
	setTimeout("changeHashAgain()", "50");
}

function changeHashAgain() {
	window.location.href += "1";
}

var storedHash = window.location.hash;
window.setInterval(function() {
	if (window.location.hash != storedHash) {
		window.location.hash = storedHash;
	}
}, 50);

/**
 * this method is used for validate the email address
 * 
 * @returns {Boolean}
 */
function emailValidator() {
	var mailId = document.getElementById('email').value;
	var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
	if (mailId.match(emailExp)) {
		alert("email is correct");
		return true;
	} else {
		email.focus();
		document.getElementById('ema').innerHTML = "Invalid id";
		document.getElementById('ema').style.color = "red";
		alert("false");
		return false;
	}
}
