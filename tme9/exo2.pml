mtype { RED, GREEN };
proctype Sensor(chan sense; chan signal) {
	bool b;
	end: // pas de deadlock ici
	do
	::sense?b -> signal!true
	od
}
int voie = 0;
proctype Feu(chan swtch; chan change) {
	bool b;
	mtype color;
	

	end_red_color:
	color = RED ;
	change!color ;
	
	end_wait_swtch:
		swtch?(b) -> if
					::color == RED -> goto end_green_color
					::color == GREEN -> goto end_red_color
					fi
	end_green_color:
		color = GREEN ;
		change!color ;
		goto end_wait_swtch

}

proctype Train(chan sensorI; chan sensorO; chan change){
	mtype color = RED;
	loin:
			do
				::true -> goto arrivee;
				::else -> 
			od
	
	arrivee:
			sensorI ! true;
			change ? color ;
						if
						:: color == GREEN -> goto voie_u;
						:: color == RED -> goto arrivee;
						fi
	voie_u:
	
		voie ++;
	
	sortie: 
			{sensorO!true; voie --; goto loin}

}

proctype Controleur(chan gI1; chan gO1; chan w1; chan w2; chan gI2; chan gO2){

	LLRR : 
				if
				::gI1 ? signal -> goto ALRR
				::gI2 ? signal -> goto LARR
				fi

	ALRR : 
				if
				::w1 ? signal -> goto ALVR
				::gI2 ? signal -> goto AARR
				fi

	LARR:	
				if
				::gI1 ? signal ->goto AARR
				::w2 ? signal ->goto LARV
				fi

	AARR:
				if 
				::w1 ? signal ->goto AAVR
				::w2 ? signal ->goto AARV
				fi
	ALVR:
				::true -> goto VLVR				
	
	VLVR:	
				if
				::gO1 ? signal ->goto SLVR
				::gI2 ? signal ->goto VAVR				
				fi
	SLVR:
				::w1 -> goto SLRR
	SLRR :
				::true -> goto LLRR
	AAVR : 
				::true -> goto VAVR
	VAVR : 
				::gO1 ? signal ->goto SAVR
	SAVR : 
				::w1 ? signal ->goto SARR
	SARR : 
				::true -> LARR
	LAVR : 
				if
				::gI1 ? signal ->goto AARV
				::true ->goto LVRV
				fi
	LVRV :
				if
				::gI1 ? signal -> goto AURV
				::gO2 ? signal -> goto LSRV
				fi
	AARV :
				::gI1 ? signal -> goto AURV

	AURV : 
				::gO2 ? signal -> goto ASRV

	ASRV : 
				
				::w2 ? signal ->goto ASRR

	ASRR : 
				::true -> ALRR

	LSRV : 
				::w2 ? signal ->goto LSRR
	LSRR : 
				::true -> LRR	

}

