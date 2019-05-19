<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreatePetsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('pets', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->integer('user_id');
            $table->string('name', 100);
            $table->string('type', 100);
            $table->string('species', 100);
            $table->string('color', 255);
            $table->text('about');
            $table->string('avatar', 255);
            $table->dateTime('birthday');
            $table->timestamps();

            $table->index(['name']);
            $table->index(['type']);
            $table->index(['species']);
            $table->index(['color']);
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('pets');
    }
}
