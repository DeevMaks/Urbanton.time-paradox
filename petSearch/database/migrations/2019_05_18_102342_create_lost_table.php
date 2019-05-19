<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateLostTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('lost', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->integer('pet_id');
            $table->string('position', 255);
            $table->text('description');
            $table->integer('price_for_info');
            $table->integer('price_for_found');
            $table->string('status', 100);
            $table->timestamps();

            $table->index(['status']);
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('lost');
    }
}
