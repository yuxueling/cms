//<!--
browserName = navigator.appName;
browserVer = parseInt(navigator.appVersion);
condition = !(((browserName.indexOf("Explorer") >= 0) && (browserVer < 4)) || ((browserName.indexOf("Netscape") >= 0) && (browserVer < 2)));
if (condition == true) {
	CanAnimate = true;
} else {
	CanAnimate = false;
}
function translator(pattern) {

/////////////////////////////////////////////////////////////
// Script to use language convertor
// By Saradhi
/////////////////////////////////////////////////////////////

	/// Configuration parameters //////////////
	var open_in_same_window = 0;
	//////////// End Of Configuration /////////////
	var my_location = unescape(document.location.toString());
	var new_location = "";
	var new_pattern = "";
	if (my_location.indexOf("translate_c?") != -1) {
		/// From google...
		var indexof_u = my_location.indexOf("u=");
		if (indexof_u == -1) {
			new_location = document.location;
		} else {
			var subs = my_location.substring(indexof_u, my_location.length);
			var ss = subs.split("&");
			new_location = ss[0].substring(2, ss[0].length);
		}
	} else {
		new_location = document.location;
	}
	indexof_p = pattern.indexOf("|");
	var isen = "";
	if (indexof_p == -1) {
		indexof_p1 = pattern.indexOf("><");
		if (indexof_p1 == -1) {
			new_pattern = pattern;
			if (pattern == "en") {
				isen = 1;
			}
		} else {
			var psplit = pattern.split("><");
			new_pattern = psplit[0] + "|" + psplit[1];
			if (psplit[1] == "en") {
				isen = 1;
			}
		}
	} else {
		var psplit = pattern.split("|");
		new_pattern = psplit[0] + "|" + psplit[1];
		if (psplit[1] == "en") {
			isen = 1;
		}
	}
	var thisurl = "";
	if (isen == 1) {
		thisurl = new_location;
	} else {
		thisurl = "http://translate.google.com/translate_c?langpair=" + new_pattern + "&u=" + new_location;
	}
	if (open_in_same_window == 1) {
		window.location.href = thisurl;
	} else {
		if (CanAnimate) {
			msgWindow = window.open("", "subwindow", "toolbar=yes,location=yes,directories=yes,status=yes,scrollbars=yes,menubar=yes,resizable=yes,left=0,top=0");
			msgWindow.focus();
			msgWindow.location.href = thisurl;
		} else {
			msgWindow = window.open(thisurl, "subwindow", "toolbar=yes,location=yes,directories=yes,status=yes,scrollbars=yes,menubar=yes,resizable=yes,left=0,top=0");
		}
	}
}
function translator1(pattern) {
	var thisurl = "http://translate.google.com/translate_c?langpair=" + pattern + "&u=" + document.location;
	if (CanAnimate) {
		msgWindow = window.open("", "subwindow", "toolbar=yes,location=yes,directories=yes,status=yes,scrollbars=yes,menubar=yes,resizable=yes,left=0,top=0");
		msgWindow.focus();
		msgWindow.location.href = thisurl;
	} else {
		msgWindow = window.open(thisurl, "subwindow", "toolbar=yes,location=yes,directories=yes,status=yes,scrollbars=yes,menubar=yes,resizable=yes,left=0,top=0");
	}
}
function showsubmenu(sid) {
	whichEl = eval("submenu" + sid);
	if (whichEl.style.display == "none") {
		eval("submenu" + sid + ".style.display=\"\";");
	} else {
		eval("submenu" + sid + ".style.display=\"none\";");
	}
}
// -->

