	switch (t->back) {
	default: Uerror("bad return move");
	case  0: goto R999; /* nothing to undo */

		 /* PROC :init: */

	case 3: // STATE 1
		;
		now.b1 = trpt->bup.oval;
		;
		goto R999;

	case 4: // STATE 2
		;
		now.b2 = trpt->bup.oval;
		;
		goto R999;

	case 5: // STATE 3
		;
		now.turn = trpt->bup.oval;
		;
		goto R999;

	case 6: // STATE 4
		;
		now.cs_counter = trpt->bup.oval;
		;
		goto R999;

	case 7: // STATE 5
		;
		;
		delproc(0, now._nr_pr-1);
		;
		goto R999;

	case 8: // STATE 6
		;
		;
		delproc(0, now._nr_pr-1);
		;
		goto R999;

	case 9: // STATE 8
		;
		p_restor(II);
		;
		;
		goto R999;

		 /* PROC proc2 */

	case 10: // STATE 1
		;
		((P1 *)this)->count = trpt->bup.oval;
		;
		goto R999;
;
		;
		
	case 12: // STATE 3
		;
		now.b2 = trpt->bup.oval;
		;
		goto R999;
;
		;
		;
		;
		
	case 15: // STATE 8
		;
		now.turn = trpt->bup.oval;
		;
		goto R999;

	case 16: // STATE 12
		;
		now.cs_counter = trpt->bup.oval;
		;
		goto R999;
;
		;
		
	case 18: // STATE 14
		;
		now.cs_counter = trpt->bup.oval;
		;
		goto R999;

	case 19: // STATE 15
		;
		now.b2 = trpt->bup.oval;
		;
		goto R999;

	case 20: // STATE 16
		;
		((P1 *)this)->count = trpt->bup.oval;
		;
		goto R999;

	case 21: // STATE 25
		;
		p_restor(II);
		;
		;
		goto R999;

		 /* PROC proc1 */

	case 22: // STATE 1
		;
		((P0 *)this)->count = trpt->bup.oval;
		;
		goto R999;
;
		;
		
	case 24: // STATE 3
		;
		now.b1 = trpt->bup.oval;
		;
		goto R999;
;
		;
		;
		;
		
	case 27: // STATE 8
		;
		now.turn = trpt->bup.oval;
		;
		goto R999;

	case 28: // STATE 12
		;
		now.cs_counter = trpt->bup.oval;
		;
		goto R999;
;
		;
		
	case 30: // STATE 14
		;
		now.cs_counter = trpt->bup.oval;
		;
		goto R999;

	case 31: // STATE 15
		;
		now.b1 = trpt->bup.oval;
		;
		goto R999;

	case 32: // STATE 16
		;
		((P0 *)this)->count = trpt->bup.oval;
		;
		goto R999;

	case 33: // STATE 25
		;
		p_restor(II);
		;
		;
		goto R999;
	}

