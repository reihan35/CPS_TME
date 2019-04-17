#define rand	pan_rand
#define pthread_equal(a,b)	((a)==(b))
#if defined(HAS_CODE) && defined(VERBOSE)
	#ifdef BFS_PAR
		bfs_printf("Pr: %d Tr: %d\n", II, t->forw);
	#else
		cpu_printf("Pr: %d Tr: %d\n", II, t->forw);
	#endif
#endif
	switch (t->forw) {
	default: Uerror("bad forward move");
	case 0:	/* if without executable clauses */
		continue;
	case 1: /* generic 'goto' or 'skip' */
		IfNotBlocked
		_m = 3; goto P999;
	case 2: /* generic 'else' */
		IfNotBlocked
		if (trpt->o_pm&1) continue;
		_m = 3; goto P999;

		 /* PROC :init: */
	case 3: // STATE 1 - mutex2.pml:58 - [b1 = 1] (0:0:1 - 1)
		IfNotBlocked
		reached[2][1] = 1;
		(trpt+1)->bup.oval = ((int)now.b1);
		now.b1 = 1;
#ifdef VAR_RANGES
		logval("b1", ((int)now.b1));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 4: // STATE 2 - mutex2.pml:59 - [b2 = 1] (0:0:1 - 1)
		IfNotBlocked
		reached[2][2] = 1;
		(trpt+1)->bup.oval = ((int)now.b2);
		now.b2 = 1;
#ifdef VAR_RANGES
		logval("b2", ((int)now.b2));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 5: // STATE 3 - mutex2.pml:60 - [turn = 1] (0:0:1 - 1)
		IfNotBlocked
		reached[2][3] = 1;
		(trpt+1)->bup.oval = now.turn;
		now.turn = 1;
#ifdef VAR_RANGES
		logval("turn", now.turn);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 6: // STATE 4 - mutex2.pml:61 - [cs_counter = 0] (0:0:1 - 1)
		IfNotBlocked
		reached[2][4] = 1;
		(trpt+1)->bup.oval = now.cs_counter;
		now.cs_counter = 0;
#ifdef VAR_RANGES
		logval("cs_counter", now.cs_counter);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 7: // STATE 5 - mutex2.pml:64 - [(run proc1(20))] (0:0:0 - 1)
		IfNotBlocked
		reached[2][5] = 1;
		if (!(addproc(II, 1, 0, 20)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 8: // STATE 6 - mutex2.pml:65 - [(run proc2(20))] (0:0:0 - 1)
		IfNotBlocked
		reached[2][6] = 1;
		if (!(addproc(II, 1, 1, 20)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 9: // STATE 8 - mutex2.pml:67 - [-end-] (0:0:0 - 1)
		IfNotBlocked
		reached[2][8] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */

		 /* PROC proc2 */
	case 10: // STATE 1 - mutex2.pml:33 - [count = 0] (0:0:1 - 1)
		IfNotBlocked
		reached[1][1] = 1;
		(trpt+1)->bup.oval = ((P1 *)this)->count;
		((P1 *)this)->count = 0;
#ifdef VAR_RANGES
		logval("proc2:count", ((P1 *)this)->count);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 11: // STATE 2 - mutex2.pml:35 - [((count<repeat))] (0:0:0 - 1)
		IfNotBlocked
		reached[1][2] = 1;
		if (!((((P1 *)this)->count<((P1 *)this)->repeat)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 12: // STATE 3 - mutex2.pml:36 - [b2 = 0] (0:0:1 - 1)
		IfNotBlocked
		reached[1][3] = 1;
		(trpt+1)->bup.oval = ((int)now.b2);
		now.b2 = 0;
#ifdef VAR_RANGES
		logval("b2", ((int)now.b2));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 13: // STATE 4 - mutex2.pml:39 - [((turn!=2))] (0:0:0 - 1)
		IfNotBlocked
		reached[1][4] = 1;
		if (!((now.turn!=2)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 14: // STATE 5 - mutex2.pml:42 - [(!(b1))] (0:0:0 - 1)
		IfNotBlocked
		reached[1][5] = 1;
		if (!( !(((int)now.b1))))
			continue;
		_m = 3; goto P999; /* 0 */
	case 15: // STATE 8 - mutex2.pml:43 - [turn = 2] (0:0:1 - 1)
		IfNotBlocked
		reached[1][8] = 1;
		(trpt+1)->bup.oval = now.turn;
		now.turn = 2;
#ifdef VAR_RANGES
		logval("turn", now.turn);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 16: // STATE 12 - mutex2.pml:45 - [cs_counter = (cs_counter+1)] (0:0:1 - 1)
		IfNotBlocked
		reached[1][12] = 1;
		(trpt+1)->bup.oval = now.cs_counter;
		now.cs_counter = (now.cs_counter+1);
#ifdef VAR_RANGES
		logval("cs_counter", now.cs_counter);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 17: // STATE 13 - mutex2.pml:46 - [assert((cs_counter==1))] (0:0:0 - 1)
		IfNotBlocked
		reached[1][13] = 1;
		spin_assert((now.cs_counter==1), "(cs_counter==1)", II, tt, t);
		_m = 3; goto P999; /* 0 */
	case 18: // STATE 14 - mutex2.pml:47 - [cs_counter = (cs_counter-1)] (0:0:1 - 1)
		IfNotBlocked
		reached[1][14] = 1;
		(trpt+1)->bup.oval = now.cs_counter;
		now.cs_counter = (now.cs_counter-1);
#ifdef VAR_RANGES
		logval("cs_counter", now.cs_counter);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 19: // STATE 15 - mutex2.pml:48 - [b2 = 1] (0:0:1 - 1)
		IfNotBlocked
		reached[1][15] = 1;
		(trpt+1)->bup.oval = ((int)now.b2);
		now.b2 = 1;
#ifdef VAR_RANGES
		logval("b2", ((int)now.b2));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 20: // STATE 16 - mutex2.pml:49 - [count = (count+1)] (0:0:1 - 1)
		IfNotBlocked
		reached[1][16] = 1;
		(trpt+1)->bup.oval = ((P1 *)this)->count;
		((P1 *)this)->count = (((P1 *)this)->count+1);
#ifdef VAR_RANGES
		logval("proc2:count", ((P1 *)this)->count);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 21: // STATE 25 - mutex2.pml:55 - [-end-] (0:0:0 - 3)
		IfNotBlocked
		reached[1][25] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */

		 /* PROC proc1 */
	case 22: // STATE 1 - mutex2.pml:8 - [count = 0] (0:0:1 - 1)
		IfNotBlocked
		reached[0][1] = 1;
		(trpt+1)->bup.oval = ((P0 *)this)->count;
		((P0 *)this)->count = 0;
#ifdef VAR_RANGES
		logval("proc1:count", ((P0 *)this)->count);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 23: // STATE 2 - mutex2.pml:10 - [((count<repeat))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][2] = 1;
		if (!((((P0 *)this)->count<((P0 *)this)->repeat)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 24: // STATE 3 - mutex2.pml:11 - [b1 = 0] (0:0:1 - 1)
		IfNotBlocked
		reached[0][3] = 1;
		(trpt+1)->bup.oval = ((int)now.b1);
		now.b1 = 0;
#ifdef VAR_RANGES
		logval("b1", ((int)now.b1));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 25: // STATE 4 - mutex2.pml:14 - [((turn!=1))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][4] = 1;
		if (!((now.turn!=1)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 26: // STATE 5 - mutex2.pml:17 - [(!(b2))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][5] = 1;
		if (!( !(((int)now.b2))))
			continue;
		_m = 3; goto P999; /* 0 */
	case 27: // STATE 8 - mutex2.pml:18 - [turn = 1] (0:0:1 - 1)
		IfNotBlocked
		reached[0][8] = 1;
		(trpt+1)->bup.oval = now.turn;
		now.turn = 1;
#ifdef VAR_RANGES
		logval("turn", now.turn);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 28: // STATE 12 - mutex2.pml:20 - [cs_counter = (cs_counter+1)] (0:0:1 - 1)
		IfNotBlocked
		reached[0][12] = 1;
		(trpt+1)->bup.oval = now.cs_counter;
		now.cs_counter = (now.cs_counter+1);
#ifdef VAR_RANGES
		logval("cs_counter", now.cs_counter);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 29: // STATE 13 - mutex2.pml:21 - [assert((cs_counter==1))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][13] = 1;
		spin_assert((now.cs_counter==1), "(cs_counter==1)", II, tt, t);
		_m = 3; goto P999; /* 0 */
	case 30: // STATE 14 - mutex2.pml:22 - [cs_counter = (cs_counter-1)] (0:0:1 - 1)
		IfNotBlocked
		reached[0][14] = 1;
		(trpt+1)->bup.oval = now.cs_counter;
		now.cs_counter = (now.cs_counter-1);
#ifdef VAR_RANGES
		logval("cs_counter", now.cs_counter);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 31: // STATE 15 - mutex2.pml:23 - [b1 = 1] (0:0:1 - 1)
		IfNotBlocked
		reached[0][15] = 1;
		(trpt+1)->bup.oval = ((int)now.b1);
		now.b1 = 1;
#ifdef VAR_RANGES
		logval("b1", ((int)now.b1));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 32: // STATE 16 - mutex2.pml:24 - [count = (count+1)] (0:0:1 - 1)
		IfNotBlocked
		reached[0][16] = 1;
		(trpt+1)->bup.oval = ((P0 *)this)->count;
		((P0 *)this)->count = (((P0 *)this)->count+1);
#ifdef VAR_RANGES
		logval("proc1:count", ((P0 *)this)->count);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 33: // STATE 25 - mutex2.pml:29 - [-end-] (0:0:0 - 3)
		IfNotBlocked
		reached[0][25] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */
	case  _T5:	/* np_ */
		if (!((!(trpt->o_pm&4) && !(trpt->tau&128))))
			continue;
		/* else fall through */
	case  _T2:	/* true */
		_m = 3; goto P999;
#undef rand
	}

