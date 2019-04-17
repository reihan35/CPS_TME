mtype:col = {rouge, vert, orange}
mtype:mode = {initializing, activated, broken}

mtype:col light_col;
mtype:mode status;
bool clignotant;
chan ch = [0] of {mtype:col, mtype:col, bool}

proctype feu(){
	mtype:col couleur1


	do
	::status == initializing ; status = activated; clignotant = false; light_col = rouge;
	::status == activated && light_col == rouge; couleur1 = light_col; light_col = vert;ch ! couleur1, light_col, clignotant;
	::status == activated && light_col == vert; couleur1 = light_col; light_col = orange;ch ! couleur1, light_col, clignotant;
	::status == activated && light_col == orange; couleur1 = light_col; light_col = rouge;ch ! couleur1, light_col, clignotant;

	::true; couleur1 = light_col;clignotant = true;light_col = orange;ch ! couleur1, light_col, clignotant; goto panne;
	od

panne:
	do
		::true; couleur1 = light_col; light_col = orange; clignotant = true; ch ! couleur1, light_col, clignotant;
	od
}


proctype panne(){
	do
		::true; status = broken;
	od
}
		

proctype obs(){
	mtype:col c1;
	mtype:col c2;
	bool cli;

	do
		::ch ? c1, c2, cli ->
			if
				::atomic{c2 == orange -> assert(cli == true || c1 != rouge)};
				::atomic{c2 == vert -> assert(c1 != orange)};
				::atomic{c2 == rouge -> assert(c1 != vert)};
				::atomic{cli == true -> assert(c2 == orange)};
			fi
	od

}

init {
	status = initializing;
	light_col = orange;
	clignotant = true;
	
	atomic{
		run feu();
		run obs();
		run panne();
	}
}







  
