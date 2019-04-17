bool ask0;
bool ask1;
byte turn;
int cs_counter;

proctype proc1(int repeat){
	int count;
	count = 0
	do
		::count < repeat->
		ask0 = true;
		do
			::ask1 == true->
			if
				::turn!=0 -> ask0 = false; turn != 0;ask0=true;
				::else->
			fi;
			::else->break;
		od;
		cs_counter = cs_counter+1;
		assert(cs_counter==1);
		cs_counter = cs_counter - 1;
		turn = 1;
		ask0 = false;
		count = count + 1;
		::else->break;
	od
	
}

proctype proc2(int repeat){
	int count;
	count = 0;
	do
		::count < repeat->
		ask1 = true;
		do
			::ask0 == true->
			if
				::turn!=1 -> ask1 = false; turn != 1;ask1=true;
				::else->
			fi;
			::else->break;
		od;
		cs_counter = cs_counter+1;
		assert(cs_counter==1);
		cs_counter = cs_counter - 1;
		turn = 0;
		ask1 = false;
		count = count + 1
		::else->break;
	od
	
}

init {
	ask0 = false;
	ask1 = false;
	turn = 0;
	cs_counter = 0;
	
	atomic{
			run proc1(20);
			run proc2(20);
		}
}
