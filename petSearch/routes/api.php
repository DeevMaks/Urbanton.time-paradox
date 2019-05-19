<?php

use Illuminate\Http\Request;
use App\Pet;
use App\User;
use App\Lost;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::get('/users', function () {
    return User::paginate(15);
});

Route::get('/users/{id}', function ($id) {
    return User::find($id);
});

Route::get('/pets', function () {
    return Pet::paginate(15);
});

Route::get('/pet/{id}', function ($id) {
    return Pet::find($id);
});

Route::get('/lost', function () {
    return Lost::paginate(15);
});

Route::get('/lost/{id}', function ($id) {
    return Lost::find($id);
});