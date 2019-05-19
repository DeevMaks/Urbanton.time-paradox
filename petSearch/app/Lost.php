<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Lost extends Model
{
  protected $table = "lost";

  public function pet()
  {
    return $this->hasOne('App\Pet');
  }
}
