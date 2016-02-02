var app = angular.module('lpn-condominio-app', []);

app.value("appConfig", {
	contextName : document.location.pathname.split("/")[1]
});