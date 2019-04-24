mtype {EAST, NORTH, SOUTH, WEST, EXIT};
chan go = [0] of {mtype}

active proctype laby(){
	entree: 
			do
			::true -> go!NORTH; goto case_51;
			::else -> 
			od
	
	case_13: 
			do
			::true -> go!EAST; goto case_23;
			::else ->
			od
	case_14:
			do 
			::true -> go!NORTH; goto case_15;
			::else->
			od
	case_15:
			do
			::true -> go!NORTH; goto exit;
			::true -> go!SOUTH; goto exit;
			::true -> go!EXIT; goto case_25;
			::else->
			od
	case_21 :
			do 
			::true -> go!EAST; goto case_31;
			::true -> go!NORTH; goto case_22;
			::else->
			od
	case_22 :
			do 
			::true -> go!EAST; goto case_32;
			::true -> go!NORTH; goto case_23;
			::true -> go!SOUTH; goto case_21;
			::else->
			od
	case_23 :
			do 
			::true -> go!WEST; goto case_13;
			::true -> go!NORTH; goto case_24;
			::true -> go!SOUTH; goto case_22;
			::else->
			od
	case_24:
			do
			::true -> go!EAST; goto case_34;
			::true -> go!SOUTH; goto case_23;
			::else -> 
			od
	case_25:
			do 
			::true -> go!WEST; goto case_15;
			::true -> go!EAST; goto case_35;
			:: else ->
			od
	
	case_31 :
			do
			::true -> go!WEST; goto case_21;
			::true -> go!EAST; goto case_41;
			::else ->
			od
	
	case_32 :
			do
			::true -> go!WEST; goto case_22;
			::true -> go!EAST; goto case_42;
			::else -> 
			od
	
	case_34 :
			do
			::true -> go!WEST; goto case_24;
			::true -> go!EAST; goto case_44;
			::else -> 
			od
	
	case_35 :
			do
			::true -> go!WEST; goto case_25;
			::true -> go!EAST; goto case_45;
			::else -> 
			od
	
	case_41 :
			do 
			::true -> go!WEST; goto case_31;
			::else -> 
			od
	
	case_42 :
			do 
			::true -> go!WEST; goto case_32;
			::true -> go!EAST; goto case_52;
			::else -> 
			od

	case_44 :
			do
			::true -> go!WEST; goto case_34;
			::true -> go!NORTH; goto case_45;
			::else ->
			od
	
	case_45:
			do
			::true -> go!WEST; goto case_35;
			::true -> go!SOUTH; goto case_44;
			::else ->
			od
	
	case_51:
			do
			::true -> go!NORTH; goto case_52;
			::else ->	
			od
	
	case_52:
			do
			::true -> go!NORTH; goto case_53;
			::true -> go!SOUTH; goto case_51;
			::true -> go!WEST; goto case_42;
			::else ->	
			od
	
	case_53:
			do 
			::true -> go!NORTH; goto case_54;
			::true -> go!SOUTH; goto case_52;
			::else ->	
			od
	
	case_54:
			do
			::true -> go!NORTH; goto case_55;
			::true -> go!SOUTH; goto case_53;
			::else ->	
			od
	
	case_55:
			do
			::true -> go!SOUTH; goto case_54;
			::else ->
			od
	exit:
		do
		::true -> go!SOUTH; goto case_15;
		::else ->
		od


}   



active proctype observateur() {
	mtype dir ;
	do
		::go?(dir) -> if
					  ::dir==EXIT -> printf("go EXIT") ; goto exit
					  ::else -> printf("go %e\n", dir) 
					  // %e pour afficher un mtype
	fi
	od
	exit:
}
