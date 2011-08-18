// This is a test harness for your module
// You should do something interesting in this harness 
// to test out the module and to provide instructions 
// to users on how to use it by example.


// open a single window
var window = Ti.UI.createWindow({
	backgroundColor:'white'
});
var label = Ti.UI.createLabel();
window.add(label);
window.open();

// TODO: write your module tests here
var mediaquery = require('com.kosso.mediaquery');
Ti.API.info("module is => " + mediaquery);

label.text = mediaquery.example();

Ti.API.info("module exampleProp is => " + mediaquery.exampleProp);
mediaquery.exampleProp = "This is a test value";

if (Ti.Platform.name == "android") {
	var proxy = mediaquery.createExample({message: "Creating an example Proxy"});
	proxy.printMessage("Hello world!");
}