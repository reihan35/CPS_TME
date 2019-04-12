	switch (t->back) {
	default: Uerror("bad return move");
	case  0: goto R999; /* nothing to undo */

		 /* PROC :init: */

	case 3: // STATE 1
		;
		now.status = trpt->bup.oval;
		;
		goto R999;

	case 4: // STATE 2
		;
		now.light_col = trpt->bup.oval;
		;
		goto R999;

	case 5: // STATE 3
		;
		now.clignotant = trpt->bup.oval;
		;
		goto R999;

	case 6: // STATE 4
		;
		;
		delproc(0, now._nr_pr-1);
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

		 /* PROC obs */

	case 10: // STATE 1
		;
		XX = 1;
		unrecv(now.ch, XX-1, 0, ((P2 *)this)->c1, 1);
		unrecv(now.ch, XX-1, 1, ((P2 *)this)->c2, 0);
		unrecv(now.ch, XX-1, 2, ((int)((P2 *)this)->cli), 0);
		((P2 *)this)->c1 = trpt->bup.ovals[0];
		((P2 *)this)->c2 = trpt->bup.ovals[1];
		((P2 *)this)->cli = trpt->bup.ovals[2];
		;
		;
		ungrab_ints(trpt->bup.ovals, 3);
		goto R999;

	case 11: // STATE 2
		;
	/* 0 */	((P2 *)this)->c2 = trpt->bup.oval;
		;
		;
		goto R999;

	case 12: // STATE 5
		;
	/* 0 */	((P2 *)this)->c2 = trpt->bup.oval;
		;
		;
		goto R999;

	case 13: // STATE 8
		;
	/* 0 */	((P2 *)this)->c2 = trpt->bup.oval;
		;
		;
		goto R999;

	case 14: // STATE 11
		;
	/* 0 */	((P2 *)this)->cli = trpt->bup.oval;
		;
		;
		goto R999;

	case 15: // STATE 19
		;
		p_restor(II);
		;
		;
		goto R999;

		 /* PROC panne */

	case 16: // STATE 2
		;
		now.status = trpt->bup.oval;
		;
		goto R999;

	case 17: // STATE 6
		;
		p_restor(II);
		;
		;
		goto R999;

		 /* PROC feu */
;
		;
		
	case 19: // STATE 2
		;
		now.status = trpt->bup.oval;
		;
		goto R999;

	case 20: // STATE 3
		;
		now.clignotant = trpt->bup.oval;
		;
		goto R999;

	case 21: // STATE 4
		;
		now.light_col = trpt->bup.oval;
		;
		goto R999;
;
		;
		
	case 23: // STATE 6
		;
		((P0 *)this)->couleur1 = trpt->bup.oval;
		;
		goto R999;

	case 24: // STATE 7
		;
		now.light_col = trpt->bup.oval;
		;
		goto R999;

	case 25: // STATE 8
		;
		_m = unsend(now.ch);
		;
		goto R999;
;
		;
		
	case 27: // STATE 10
		;
		((P0 *)this)->couleur1 = trpt->bup.oval;
		;
		goto R999;

	case 28: // STATE 11
		;
		now.light_col = trpt->bup.oval;
		;
		goto R999;

	case 29: // STATE 12
		;
		_m = unsend(now.ch);
		;
		goto R999;
;
		;
		
	case 31: // STATE 14
		;
		((P0 *)this)->couleur1 = trpt->bup.oval;
		;
		goto R999;

	case 32: // STATE 15
		;
		now.light_col = trpt->bup.oval;
		;
		goto R999;

	case 33: // STATE 16
		;
		_m = unsend(now.ch);
		;
		goto R999;

	case 34: // STATE 18
		;
		((P0 *)this)->couleur1 = trpt->bup.oval;
		;
		goto R999;

	case 35: // STATE 19
		;
		now.clignotant = trpt->bup.oval;
		;
		goto R999;

	case 36: // STATE 20
		;
		now.light_col = trpt->bup.oval;
		;
		goto R999;

	case 37: // STATE 21
		;
		_m = unsend(now.ch);
		;
		goto R999;

	case 38: // STATE 27
		;
		((P0 *)this)->couleur1 = trpt->bup.oval;
		;
		goto R999;

	case 39: // STATE 28
		;
		now.light_col = trpt->bup.oval;
		;
		goto R999;

	case 40: // STATE 29
		;
		now.clignotant = trpt->bup.oval;
		;
		goto R999;

	case 41: // STATE 30
		;
		_m = unsend(now.ch);
		;
		goto R999;

	case 42: // STATE 34
		;
		p_restor(II);
		;
		;
		goto R999;
	}

