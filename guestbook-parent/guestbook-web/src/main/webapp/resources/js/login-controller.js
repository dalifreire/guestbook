app.controller('loginController', function($rootScope, $scope, $http, $location, appConfig) {

	console.log("login-controller working!");
	

	$scope.credentials = {};
	$scope.login = function() {
		
		console.log("Realizando login para username '" + $scope.credentials.username + "'...");
		
		$http({
                method: 'POST',
                url: '/' + appConfig.contextName + '/login',
                data: "username=" + $scope.credentials.username + "&password=" + $scope.credentials.password,
                headers: {
                	"Content-Type": "application/x-www-form-urlencoded",
                	"X-Login-Ajax-call": "true" }
            }).then(
            		function successCallback(response) {
            			if (response.data == 'ok') {
            				console.log("Login realizado com sucesso");
                			$scope.loginError = false;
                			window.location.replace('/' + appConfig.contextName + '/pages/restricted/home.html');
            			} else {
                			console.log("Login falhou");
                			$scope.loginError = true;
            			}
            		}, 
            		function errorCallback(response) {
            			console.log("Login falhou");
            			$scope.loginError = true;
            		});
	};

	$scope.logout = function() {
		
		console.log("Realizando logout...");
		
		$http.post('/' + appConfig.contextName + '/logout', {}).success(function() {
			console.log("Logout realizado com sucesso");
			$rootScope.authenticated = false;
			document.location = '/' + appConfig.contextName;
		}).error(function(data) {
			console.log("Logout falhou")
			$rootScope.authenticated = false;
		});
	};
	
	
	$scope.esqueciMinhaSenha = {};
	$scope.redefinirSenha = function() {
		
		console.log("Redefinindo senha...");
		
		$http.post('/' + appConfig.contextName + '/rest/login/redefinirSenha', $scope.esqueciMinhaSenha.email).success(function(data) {
			
			if (data.codRetorno == 0) {
				console.log("Redefinicao de senha realizada com sucesso");
				$scope.esqueciMinhaSenha.error = false;
				$scope.esqueciMinhaSenha.success = true;
				$scope.esqueciMinhaSenha.successMessage = "Uma nova senha será encaminhada ao seu e-mail nos próximos instantes.";
				$scope.esqueciMinhaSenha.email = "";
			} else if (data.codRetorno == -3) {
				console.log("E-mail não encontrado");
				$scope.esqueciMinhaSenha.success = false;
				$scope.esqueciMinhaSenha.error = true;
				$scope.esqueciMinhaSenha.errorMessage = data.msgRetorno;
			} else {
				console.log("Falha ao redefinir senha");
				$scope.esqueciMinhaSenha.success = false;
				$scope.esqueciMinhaSenha.error = true;
				$scope.esqueciMinhaSenha.errorMessage = "Não foi possível redefinir a sua senha. Por favor, tente novamente.";
			}
			
		}).error(function(data){
			console.log("Falha ao redefinir senha");
			$scope.esqueciMinhaSenha.success = false;
			$scope.esqueciMinhaSenha.error = true;
			$scope.esqueciMinhaSenha.errorMessage = "Não foi possível redefinir a sua senha. Por favor, tente novamente.";
		});
	};
	
	
	$scope.cadastreSe = {};
	$scope.cadastrar = function() {
		
		console.log("Cadastrando novo usuário...");
		
		$http.post('/' + appConfig.contextName + '/rest/login/cadastrar', $scope.cadastrarUsuarioDto).success(function(data) {
			
			if (data.codRetorno === 0) {
				console.log("Cadastro realizado com sucesso");
				$scope.cadastreSe.error = false;
				$scope.cadastreSe.success = true;
				$scope.cadastreSe.successMessage = "Cadastro realizado com sucesso. Você receberá um e-mail com instruções nos próximos instantes.";
				$scope.cadastrarUsuarioDto = {};
			} else if (data.codRetorno === -3) {
				console.log("Parâmetros inválidos");
				$scope.cadastreSe.success = false;
				$scope.cadastreSe.error = true;
				$scope.cadastreSe.errorMessage = data.msgRetorno;
			} else {
				console.log("Falha ao cadastrar usuário");
				$scope.cadastreSe.success = false;
				$scope.cadastreSe.error = true;
				$scope.cadastreSe.errorMessage = "Não foi possível realizar o cadastro. Por favor, tente novamente.";
			}
			
		}).error(function(data){
			console.log("Falha ao cadastrar usuário");
			$scope.cadastreSe.success = false;
			$scope.cadastreSe.error = true;
			$scope.cadastreSe.errorMessage = "Não foi possível realizar o cadastro. Por favor, tente novamente.";
		});
		
	};
	
});
