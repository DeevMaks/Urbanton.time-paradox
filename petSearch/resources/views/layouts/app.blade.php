<!DOCTYPE html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- CSRF Token -->
    <meta name="csrf-token" content="{{ csrf_token() }}">

    <title>{{ config('app.name', 'Laravel') }}</title>



    <!-- Scripts -->
    <script src="{{ asset('js/app.js') }}" defer></script>

    <!-- Fonts -->
    <link rel="dns-prefetch" href="//fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css?family=PT+Sans+Narrow&display=swap" rel="stylesheet"> 

    <!-- Styles -->
    <link href="{{ asset('css/app.css') }}" rel="stylesheet">
    <link href="{{ asset('css/main.css') }}" rel="stylesheet">
</head>
<body>
    <div class="page">
    <div id="left-padding"></div>
    <div id="wrapper">
        <header class="col-md-12">
                <div id="main_logo">
                    <a href="#"><img src="img/logo.png"></a>
                </div>
                <div id="vert-delim"></div>
                <nav>
                    @guest

                    @else
                        <div class="icon">
                            <a href="#">
                                <img src="img/baby.png">
                                Питомец
                            </a>
                        </div>
                        <div class="icon">
                            <a href="#">
                                <img src="img/add.png">
                                Добавить
                            </a>
                        </div>
                    @endguest
                    <div class="icon">
                        <a href="#">
                            <img src="img/mypet.png">
                            Магазин
                        </a>
                    </div>
                    <div class="icon">
                        <a href="#">
                            <img src="img/chat.png">
                            Питомник
                        </a>
                    </div>
                    <div class="icon">
                        <a href="#">
                            <img src="img/lost.png">
                            Розыск
                        </a>
                    </div>
                </nav>
        </header>
        <div class="row">
            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">События</div>

                    <div class="card-body profile">
                       
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card">
                    <div class="card-header">Профиль</div>

                    <div class="card-body profile">
                        @guest
                            <img class="avatar" src="img/avatarka.jpg">
                            <br />
                            <a href="{{ route('login') }}">
                                {{ __('Вход') }}
                            </a>
                            <br />
                            @if (Route::has('register'))
                                <a href="{{ route('register') }}">
                                    {{ __('Регистрация') }}
                                </a>
                            @endif
                        @else
                            <img class="avatar" src="img/avatarka.jpg">
                            {{ Auth::user()->name }}
                            <a class="dropdown-item" href="{{ route('logout') }}" onclick="event.preventDefault(); document.getElementById('logout-form').submit();">
                                {{ __('Выйти') }}
                            </a>

                            <form id="logout-form" action="{{ route('logout') }}" method="POST" style="display: none;">
                                @csrf
                            </form>
                        @endguest
                    </div>
                </div>
            </div>
        </div>
        <main>
            @yield('content')
        </main>
    </div>
    <div id="right-padding"></div>
    </div>
    <footer>
        Time-Paradox
    </footer>
</body>
</html>
