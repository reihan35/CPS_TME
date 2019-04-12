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
	case 3: // STATE 1 - feu.pml:54 - [status = 3] (0:0:1 - 1)
		IfNotBlocked
		reached[3][1] = 1;
		(trpt+1)->bup.oval = now.status;
		now.status = 3;
#ifdef VAR_RANGES
		logval("status", now.status);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 4: // STATE 2 - feu.pml:55 - [light_col = 1] (0:0:1 - 1)
		IfNotBlocked
		reached[3][2] = 1;
		(trpt+1)->bup.oval = now.light_col;
		now.light_col = 1;
#ifdef VAR_RANGES
		logval("light_col", now.light_col);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 5: // STATE 3 - feu.pml:56 - [clignotant = 1] (0:0:1 - 1)
		IfNotBlocked
		reached[3][3] = 1;
		(trpt+1)->bup.oval = ((int)now.clignotant);
		now.clignotant = 1;
#ifdef VAR_RANGES
		logval("clignotant", ((int)now.clignotant));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 6: // STATE 4 - feu.pml:59 - [(run feu())] (0:0:0 - 1)
		IfNotBlocked
		reached[3][4] = 1;
		if (!(addproc(II, 1, 0)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 7: // STATE 5 - feu.pml:60 - [(run obs())] (0:0:0 - 1)
		IfNotBlocked
		reached[3][5] = 1;
		if (!(addproc(II, 1, 2)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 8: // STATE 6 - feu.pml:61 - [(run panne())] (0:0:0 - 1)
		IfNotBlocked
		reached[3][6] = 1;
		if (!(addproc(II, 1, 1)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 9: // STATE 8 - feu.pml:63 - [-end-] (0:0:0 - 1)
		IfNotBlocked
		reached[3][8] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */

		 /* PROC obs */
	case 10: // STATE 1 - feu.pml:42 - [ch?c1,c2,cli] (0:0:3 - 1)
		reached[2][1] = 1;
		if (boq != now.ch) continue;
		if (q_len(now.ch) == 0) continue;

		XX=1;
		(trpt+1)->bup.ovals = grab_ints(3);
		(trpt+1)->bup.ovals[0] = ((P2 *)this)->c1;
		(trpt+1)->bup.ovals[1] = ((P2 *)this)->c2;
		(trpt+1)->bup.ovals[2] = ((int)((P2 *)this)->cli);
		;
		((P2 *)this)->c1 = qrecv(now.ch, XX-1, 0, 0);
#ifdef VAR_RANGES
		logval("obs:c1", ((P2 *)this)->c1);
#endif
		;
		((P2 *)this)->c2 = qrecv(now.ch, XX-1, 1, 0);
#ifdef VAR_RANGES
		logval("obs:c2", ((P2 *)this)->c2);
#endif
		;
		((P2 *)this)->cli = qrecv(now.ch, XX-1, 2, 1);
#ifdef VAR_RANGES
		logval("obs:cli", ((int)((P2 *)this)->cli));
#endif
		;
		
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[32];
			sprintf(simvals, "%d?", now.ch);
		sprintf(simtmp, "%d", ((P2 *)this)->c1); strcat(simvals, simtmp);		strcat(simvals, ",");
		sprintf(simtmp, "%d", ((P2 *)this)->c2); strcat(simvals, simtmp);		strcat(simvals, ",");
		sprintf(simtmp, "%d", ((int)((P2 *)this)->cli)); strcat(simvals, simtmp);		}
#endif
		if (q_zero(now.ch))
		{	boq = -1;
#ifndef NOFAIR
			if (fairness
			&& !(trpt->o_pm&32)
			&& (now._a_t&2)
			&&  now._cnt[now._a_t&1] == II+2)
			{	now._cnt[now._a_t&1] -= 1;
#ifdef VERI
				if (II == 1)
					now._cnt[now._a_t&1] = 1;
#endif
#ifdef DEBUG
			printf("%3ld: proc %d fairness ", depth, II);
			printf("Rule 2: --cnt to %d (%d)\n",
				now._cnt[now._a_t&1], now._a_t);
#endif
				trpt->o_pm |= (32|64);
			}
#endif

		};
		_m = 4; goto P999; /* 0 */
	case 11: // STATE 2 - feu.pml:44 - [((c2==1))] (16:0:1 - 1)
		IfNotBlocked
		reached[2][2] = 1;
		if (!((((P2 *)this)->c2==1)))
			continue;
		if (TstOnly) return 1; /* TT */
		/* dead 1: c2 */  (trpt+1)->bup.oval = ((P2 *)this)->c2;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P2 *)this)->c2 = 0;
		/* merge: assert(((cli==1)||(c1!=3)))(0, 3, 16) */
		reached[2][3] = 1;
		spin_assert(((((int)((P2 *)this)->cli)==1)||(((P2 *)this)->c1!=3)), "((cli==1)||(c1!=3))", II, tt, t);
		/* merge: .(goto)(0, 15, 16) */
		reached[2][15] = 1;
		;
		/* merge: .(goto)(0, 17, 16) */
		reached[2][17] = 1;
		;
		_m = 3; goto P999; /* 3 */
	case 12: // STATE 5 - feu.pml:45 - [((c2==2))] (16:0:1 - 1)
		IfNotBlocked
		reached[2][5] = 1;
		if (!((((P2 *)this)->c2==2)))
			continue;
		if (TstOnly) return 1; /* TT */
		/* dead 1: c2 */  (trpt+1)->bup.oval = ((P2 *)this)->c2;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P2 *)this)->c2 = 0;
		/* merge: assert((c1!=1))(0, 6, 16) */
		reached[2][6] = 1;
		spin_assert((((P2 *)this)->c1!=1), "(c1!=1)", II, tt, t);
		/* merge: .(goto)(0, 15, 16) */
		reached[2][15] = 1;
		;
		/* merge: .(goto)(0, 17, 16) */
		reached[2][17] = 1;
		;
		_m = 3; goto P999; /* 3 */
	case 13: // STATE 8 - feu.pml:46 - [((c2==3))] (16:0:1 - 1)
		IfNotBlocked
		reached[2][8] = 1;
		if (!((((P2 *)this)->c2==3)))
			continue;
		if (TstOnly) return 1; /* TT */
		/* dead 1: c2 */  (trpt+1)->bup.oval = ((P2 *)this)->c2;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P2 *)this)->c2 = 0;
		/* merge: assert((c1!=2))(0, 9, 16) */
		reached[2][9] = 1;
		spin_assert((((P2 *)this)->c1!=2), "(c1!=2)", II, tt, t);
		/* merge: .(goto)(0, 15, 16) */
		reached[2][15] = 1;
		;
		/* merge: .(goto)(0, 17, 16) */
		reached[2][17] = 1;
		;
		_m = 3; goto P999; /* 3 */
	case 14: // STATE 11 - feu.pml:47 - [((cli==1))] (16:0:1 - 1)
		IfNotBlocked
		reached[2][11] = 1;
		if (!((((int)((P2 *)this)->cli)==1)))
			continue;
		if (TstOnly) return 1; /* TT */
		/* dead 1: cli */  (trpt+1)->bup.oval = ((P2 *)this)->cli;
#ifdef HAS_CODE
		if (!readtrail)
#endif
			((P2 *)this)->cli = 0;
		/* merge: assert((c2==1))(0, 12, 16) */
		reached[2][12] = 1;
		spin_assert((((P2 *)this)->c2==1), "(c2==1)", II, tt, t);
		/* merge: .(goto)(0, 15, 16) */
		reached[2][15] = 1;
		;
		/* merge: .(goto)(0, 17, 16) */
		reached[2][17] = 1;
		;
		_m = 3; goto P999; /* 3 */
	case 15: // STATE 19 - feu.pml:51 - [-end-] (0:0:0 - 1)
		IfNotBlocked
		reached[2][19] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */

		 /* PROC panne */
	case 16: // STATE 2 - feu.pml:31 - [status = 1] (0:0:1 - 1)
		IfNotBlocked
		reached[1][2] = 1;
		(trpt+1)->bup.oval = now.status;
		now.status = 1;
#ifdef VAR_RANGES
		logval("status", now.status);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 17: // STATE 6 - feu.pml:33 - [-end-] (0:0:0 - 1)
		IfNotBlocked
		reached[1][6] = 1;
		if (!delproc(1, II)) continue;
		_m = 3; goto P999; /* 0 */

		 /* PROC feu */
	case 18: // STATE 1 - feu.pml:14 - [((status==3))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][1] = 1;
		if (!((now.status==3)))
			continue;
		_m = 3; goto P999; /* 0 */
	case 19: // STATE 2 - feu.pml:14 - [status = 2] (0:0:1 - 1)
		IfNotBlocked
		reached[0][2] = 1;
		(trpt+1)->bup.oval = now.status;
		now.status = 2;
#ifdef VAR_RANGES
		logval("status", now.status);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 20: // STATE 3 - feu.pml:14 - [clignotant = 0] (0:0:1 - 1)
		IfNotBlocked
		reached[0][3] = 1;
		(trpt+1)->bup.oval = ((int)now.clignotant);
		now.clignotant = 0;
#ifdef VAR_RANGES
		logval("clignotant", ((int)now.clignotant));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 21: // STATE 4 - feu.pml:14 - [light_col = 3] (0:0:1 - 1)
		IfNotBlocked
		reached[0][4] = 1;
		(trpt+1)->bup.oval = now.light_col;
		now.light_col = 3;
#ifdef VAR_RANGES
		logval("light_col", now.light_col);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 22: // STATE 5 - feu.pml:15 - [(((status==2)&&(light_col==3)))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][5] = 1;
		if (!(((now.status==2)&&(now.light_col==3))))
			continue;
		_m = 3; goto P999; /* 0 */
	case 23: // STATE 6 - feu.pml:15 - [couleur1 = light_col] (0:0:1 - 1)
		IfNotBlocked
		reached[0][6] = 1;
		(trpt+1)->bup.oval = ((P0 *)this)->couleur1;
		((P0 *)this)->couleur1 = now.light_col;
#ifdef VAR_RANGES
		logval("feu:couleur1", ((P0 *)this)->couleur1);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 24: // STATE 7 - feu.pml:15 - [light_col = 2] (0:0:1 - 1)
		IfNotBlocked
		reached[0][7] = 1;
		(trpt+1)->bup.oval = now.light_col;
		now.light_col = 2;
#ifdef VAR_RANGES
		logval("light_col", now.light_col);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 25: // STATE 8 - feu.pml:15 - [ch!couleur1,light_col,clignotant] (0:0:0 - 1)
		IfNotBlocked
		reached[0][8] = 1;
		if (q_len(now.ch))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.ch);
		sprintf(simtmp, "%d", ((P0 *)this)->couleur1); strcat(simvals, simtmp);		strcat(simvals, ",");
		sprintf(simtmp, "%d", now.light_col); strcat(simvals, simtmp);		strcat(simvals, ",");
		sprintf(simtmp, "%d", ((int)now.clignotant)); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.ch, 0, ((P0 *)this)->couleur1, now.light_col, ((int)now.clignotant), 3);
		{ boq = now.ch; };
		_m = 2; goto P999; /* 0 */
	case 26: // STATE 9 - feu.pml:16 - [(((status==2)&&(light_col==2)))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][9] = 1;
		if (!(((now.status==2)&&(now.light_col==2))))
			continue;
		_m = 3; goto P999; /* 0 */
	case 27: // STATE 10 - feu.pml:16 - [couleur1 = light_col] (0:0:1 - 1)
		IfNotBlocked
		reached[0][10] = 1;
		(trpt+1)->bup.oval = ((P0 *)this)->couleur1;
		((P0 *)this)->couleur1 = now.light_col;
#ifdef VAR_RANGES
		logval("feu:couleur1", ((P0 *)this)->couleur1);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 28: // STATE 11 - feu.pml:16 - [light_col = 1] (0:0:1 - 1)
		IfNotBlocked
		reached[0][11] = 1;
		(trpt+1)->bup.oval = now.light_col;
		now.light_col = 1;
#ifdef VAR_RANGES
		logval("light_col", now.light_col);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 29: // STATE 12 - feu.pml:16 - [ch!couleur1,light_col,clignotant] (0:0:0 - 1)
		IfNotBlocked
		reached[0][12] = 1;
		if (q_len(now.ch))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.ch);
		sprintf(simtmp, "%d", ((P0 *)this)->couleur1); strcat(simvals, simtmp);		strcat(simvals, ",");
		sprintf(simtmp, "%d", now.light_col); strcat(simvals, simtmp);		strcat(simvals, ",");
		sprintf(simtmp, "%d", ((int)now.clignotant)); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.ch, 0, ((P0 *)this)->couleur1, now.light_col, ((int)now.clignotant), 3);
		{ boq = now.ch; };
		_m = 2; goto P999; /* 0 */
	case 30: // STATE 13 - feu.pml:17 - [(((status==2)&&(light_col==1)))] (0:0:0 - 1)
		IfNotBlocked
		reached[0][13] = 1;
		if (!(((now.status==2)&&(now.light_col==1))))
			continue;
		_m = 3; goto P999; /* 0 */
	case 31: // STATE 14 - feu.pml:17 - [couleur1 = light_col] (0:0:1 - 1)
		IfNotBlocked
		reached[0][14] = 1;
		(trpt+1)->bup.oval = ((P0 *)this)->couleur1;
		((P0 *)this)->couleur1 = now.light_col;
#ifdef VAR_RANGES
		logval("feu:couleur1", ((P0 *)this)->couleur1);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 32: // STATE 15 - feu.pml:17 - [light_col = 3] (0:0:1 - 1)
		IfNotBlocked
		reached[0][15] = 1;
		(trpt+1)->bup.oval = now.light_col;
		now.light_col = 3;
#ifdef VAR_RANGES
		logval("light_col", now.light_col);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 33: // STATE 16 - feu.pml:17 - [ch!couleur1,light_col,clignotant] (0:0:0 - 1)
		IfNotBlocked
		reached[0][16] = 1;
		if (q_len(now.ch))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.ch);
		sprintf(simtmp, "%d", ((P0 *)this)->couleur1); strcat(simvals, simtmp);		strcat(simvals, ",");
		sprintf(simtmp, "%d", now.light_col); strcat(simvals, simtmp);		strcat(simvals, ",");
		sprintf(simtmp, "%d", ((int)now.clignotant)); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.ch, 0, ((P0 *)this)->couleur1, now.light_col, ((int)now.clignotant), 3);
		{ boq = now.ch; };
		_m = 2; goto P999; /* 0 */
	case 34: // STATE 18 - feu.pml:19 - [couleur1 = light_col] (0:0:1 - 1)
		IfNotBlocked
		reached[0][18] = 1;
		(trpt+1)->bup.oval = ((P0 *)this)->couleur1;
		((P0 *)this)->couleur1 = now.light_col;
#ifdef VAR_RANGES
		logval("feu:couleur1", ((P0 *)this)->couleur1);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 35: // STATE 19 - feu.pml:19 - [clignotant = 1] (0:0:1 - 1)
		IfNotBlocked
		reached[0][19] = 1;
		(trpt+1)->bup.oval = ((int)now.clignotant);
		now.clignotant = 1;
#ifdef VAR_RANGES
		logval("clignotant", ((int)now.clignotant));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 36: // STATE 20 - feu.pml:19 - [light_col = 1] (0:0:1 - 1)
		IfNotBlocked
		reached[0][20] = 1;
		(trpt+1)->bup.oval = now.light_col;
		now.light_col = 1;
#ifdef VAR_RANGES
		logval("light_col", now.light_col);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 37: // STATE 21 - feu.pml:19 - [ch!couleur1,light_col,clignotant] (0:0:0 - 1)
		IfNotBlocked
		reached[0][21] = 1;
		if (q_len(now.ch))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.ch);
		sprintf(simtmp, "%d", ((P0 *)this)->couleur1); strcat(simvals, simtmp);		strcat(simvals, ",");
		sprintf(simtmp, "%d", now.light_col); strcat(simvals, simtmp);		strcat(simvals, ",");
		sprintf(simtmp, "%d", ((int)now.clignotant)); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.ch, 0, ((P0 *)this)->couleur1, now.light_col, ((int)now.clignotant), 3);
		{ boq = now.ch; };
		_m = 2; goto P999; /* 0 */
	case 38: // STATE 27 - feu.pml:24 - [couleur1 = light_col] (0:0:1 - 1)
		IfNotBlocked
		reached[0][27] = 1;
		(trpt+1)->bup.oval = ((P0 *)this)->couleur1;
		((P0 *)this)->couleur1 = now.light_col;
#ifdef VAR_RANGES
		logval("feu:couleur1", ((P0 *)this)->couleur1);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 39: // STATE 28 - feu.pml:24 - [light_col = 1] (0:0:1 - 1)
		IfNotBlocked
		reached[0][28] = 1;
		(trpt+1)->bup.oval = now.light_col;
		now.light_col = 1;
#ifdef VAR_RANGES
		logval("light_col", now.light_col);
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 40: // STATE 29 - feu.pml:24 - [clignotant = 1] (0:0:1 - 1)
		IfNotBlocked
		reached[0][29] = 1;
		(trpt+1)->bup.oval = ((int)now.clignotant);
		now.clignotant = 1;
#ifdef VAR_RANGES
		logval("clignotant", ((int)now.clignotant));
#endif
		;
		_m = 3; goto P999; /* 0 */
	case 41: // STATE 30 - feu.pml:24 - [ch!couleur1,light_col,clignotant] (0:0:0 - 1)
		IfNotBlocked
		reached[0][30] = 1;
		if (q_len(now.ch))
			continue;
#ifdef HAS_CODE
		if (readtrail && gui) {
			char simtmp[64];
			sprintf(simvals, "%d!", now.ch);
		sprintf(simtmp, "%d", ((P0 *)this)->couleur1); strcat(simvals, simtmp);		strcat(simvals, ",");
		sprintf(simtmp, "%d", now.light_col); strcat(simvals, simtmp);		strcat(simvals, ",");
		sprintf(simtmp, "%d", ((int)now.clignotant)); strcat(simvals, simtmp);		}
#endif
		
		qsend(now.ch, 0, ((P0 *)this)->couleur1, now.light_col, ((int)now.clignotant), 3);
		{ boq = now.ch; };
		_m = 2; goto P999; /* 0 */
	case 42: // STATE 34 - feu.pml:26 - [-end-] (0:0:0 - 1)
		IfNotBlocked
		reached[0][34] = 1;
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

