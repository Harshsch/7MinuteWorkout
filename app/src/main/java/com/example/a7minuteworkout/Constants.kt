package com.example.a7minuteworkout

object Constants {
    fun defaultExerciseList(): ArrayList<ExcerciseModel> {
        val exerciselist = ArrayList<ExcerciseModel>()
        val jumpingJack = ExcerciseModel(
            1,"Jumping Jack",R.drawable.ic_jumping_jacks,false,false
        )
        exerciselist.add(jumpingJack)
        val highKnees = ExcerciseModel(
            2,"High Knees",R.drawable.ic_high_knees_running_in_place,false,false
        )
        exerciselist.add(highKnees)
        val lunge = ExcerciseModel(
            3,"Lunge ",R.drawable.ic_lunge,false,false
        )
        exerciselist.add(lunge)
        val abdominalCrunches = ExcerciseModel(
            4,"Abdominal Crunches",R.drawable.ic_abdominal_crunch,false,false
        )
        exerciselist.add(abdominalCrunches)
        val plank = ExcerciseModel(
            5,"Plank",R.drawable.ic_plank,false,false
        )
        exerciselist.add(plank)
        val sidePlank = ExcerciseModel(
            6,"Side Plank",R.drawable.ic_side_plank,false,false
        )
        exerciselist.add(sidePlank)
        val pushUp = ExcerciseModel(
            7,"Push Up",R.drawable.ic_push_up,false,false
        )
        exerciselist.add(pushUp)
        val pushUpandRotation = ExcerciseModel(
            8,"Push Up and Rotation",R.drawable.ic_push_up_and_rotation,false,false
        )
        exerciselist.add(pushUpandRotation)
        val squat = ExcerciseModel(
            9,"Squat",R.drawable.ic_squat,false,false
        )
        exerciselist.add(squat)
        val stepUpOntoChair = ExcerciseModel(
            10,"Step Up Onto Chair",R.drawable.ic_step_up_onto_chair,false,false
        )
        exerciselist.add(stepUpOntoChair)
        val tricepsDipOnChair = ExcerciseModel(
        11,"Triceps Dip On Chair",R.drawable.ic_triceps_dip_on_chair,false,false
    )
        exerciselist.add(tricepsDipOnChair)
        val wallSit = ExcerciseModel(
            12,"Wall Sit",R.drawable.ic_wall_sit,false,false
        )
        exerciselist.add(wallSit)
        return exerciselist

    }
}