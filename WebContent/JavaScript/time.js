

var sec ;
var min;

function setTiming(minute,second){
sec = second; // set the seconds
min = minute; // set the minutes

countDown();

}
/**
 * this method is used for count down the time in seconds
 */
function countDown() {
	sec--;
	if (sec == -01) {
		sec = 59;
		min = min - 1;
	} else {
		//min = min;
	}

	if (sec <= 9) {
		sec = "0" + sec;
	}

	time = (min <= 9 ? "0" + min : min) + " min and " + sec + " sec ";
	document.getElementById('theTime').innerHTML = time;
	document.getElementById("minute").value=min;
	document.getElementById("second").value=sec;
	SD = window.setTimeout("countDown();", 1000);
	
	if (min == '0' && sec == '00') {
		alert("Your Times Up!!");
		var selectButton = document.getElementById('CompleteTest');
	    selectButton.click();
		sec = "00";
		window.clearTimeout(SD);
	}
}
window.onload = countDown;
