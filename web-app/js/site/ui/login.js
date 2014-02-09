UI.Login = {};

// --- Login
// -- Actions
UI.Login.ShowLogIn = function()
{
	$j('#zz-modal-login').modal('show');
};

UI.Login.LogIn = function()
{
	var username = $j('#zz-modal-login-username').val();
	var password = $j('#zz-modal-login-password').val();
	Site.logIn(username, password);
};

UI.Login.LogOut = function()
{
	Site.logOut();
};

// -- Views
UI.Login.onLogOn = function()
{
	$j('#zz-modal-login').modal('hide');
	$j('#nav_myquestions').show();
	$j('#nav_myanswers').show();
	$j('#nav_mycomments').show();
	$j('#nav_messages').show();
	$j('#nav_dropdown_options').show();
	$j('#nav_login').hide();
	$j('#nav_register').hide();

	UI.Sidebar.show();
};

UI.Login.onNotLogOn = function()
{
	$j('#nav_myquestions').hide();
	$j('#nav_myanswers').hide();
	$j('#nav_mycomments').hide();
	$j('#nav_messages').hide();
	$j('#nav_dropdown_options').hide();
	$j('#nav_login').show();
	$j('#nav_register').show();

	UI.Sidebar.hide(); 
};

UI.Login.onBadID = function()
{
	$j('#zz-modal-login-error').show();
};

UI.Login.onLogError = function()
{
	console.log('Logging error');
};

// ---- Register

// -- Actions
UI.Login.ShowRegister = function()
{
	$j('#zz-modal-register').modal('show');
};

UI.Login.Register = function()
{
	var username = $j('#zz-modal-register-username').val();
	var password = $j('#zz-modal-register-password').val();
	var email = $j('#zz-modal-register-email').val();
	
	if (username.length < 4)
	{
		$j('#zz-modal-register-error').text('Username is too short. (minimum 5 characters)');
		$j('#zz-modal-register-error').show();
	}
	else if (password.length < 5)
	{
		$j('#zz-modal-register-error').text('Password is too short. (minimum 6 characters)');
		$j('#zz-modal-register-error').show();
	}
	else
	{
		$j('#zz-modal-register-error').hide();
		Site.register(username, password, email);
	}
};

// -- Views
UI.Login.onRegistered = function()
{
	$j('#zz-modal-register').modal('hide');
	var username = $j('#zz-modal-register-username').val();
	var password = $j('#zz-modal-register-password').val();
	Site.logIn(username, password);
};

UI.Login.onRegisterError = function()
{
	$j('#zz-modal-register-error').text('The username or password already exists.');
	$j('#zz-modal-register-error').show();
};