package zkgbai.military;

import java.util.ArrayList;
import java.util.List;

import com.springrts.ai.oo.AIFloat3;
import com.springrts.ai.oo.clb.Unit;

public class Squad {
	List<Fighter> fighters;
	public float metalValue;
	public char status;
	public float income;
	public boolean assigned;
	public AIFloat3 target;
	
	public Squad(){
		this.fighters = new ArrayList<Fighter>();
		this.metalValue = 0;
		this.assigned = false;
		this.status = 'f';
		// f = forming
		// r = rallying
		// a = attacking
	}

	public void addUnit(Fighter f, int frame){
		fighters.add(f);
		f.squad = this;
		metalValue = metalValue + f.metalValue;
		f.getUnit().setMoveState(1, (short) 0, frame+30);
		f.fightTo(target, frame);
	}

	public void removeUnit(Fighter f){
		fighters.remove(f);
		metalValue -= f.metalValue;
	}

	public void setTarget(AIFloat3 pos, int frame){
		// set a target for the squad to attack.
		target = pos;
		for (Fighter f:fighters){
			f.fightTo(target, frame);
		}
	}

	public void retreatTo(AIFloat3 pos, int frame){
		// set a target for the squad to attack.
		target = pos;
		for (Fighter f:fighters){
			f.moveTo(target, frame);
		}
	}

	public AIFloat3 getPos(){
		if (fighters.size() > 0){
			int count = fighters.size();
			float x = 0;
			float z = 0;
			for (Fighter f:fighters){
				x += (f.getPos().x)/count;
				z += (f.getPos().z)/count;
			}
			AIFloat3 pos = new AIFloat3();
			pos.x = x;
			pos.z = z;
			return pos;
		}
		return target;
	}

	public boolean isRallied(int frame){
		AIFloat3 pos = getPos();
		boolean rallied = true;
		for (Fighter f: fighters){
			f.fightTo(pos, frame);
			if (distance(pos, f.getPos()) > 350){
				rallied = false;
			}
		}
		return rallied;
	}

	public boolean isDead(){
		if (fighters.size() == 0){
			return true;
		}
		return false;
	}

	public List<Fighter> cutoff(){
		List<Fighter> tooFar = new ArrayList<Fighter>();
		AIFloat3 pos = getPos();
		for (Fighter f:fighters){
			if (distance(pos, f.getPos()) > 1000 && distance(target, f.getPos()) > 1000){
				tooFar.add(f);
			}
		}
		fighters.removeAll(tooFar);
		if (fighters.size() < 4 && metalValue < 1000){
			tooFar.addAll(fighters);
			fighters.clear();
		}
		return tooFar;
	}

	float distance( AIFloat3 pos1,  AIFloat3 pos2){
		float x1 = pos1.x;
		float z1 = pos1.z;
		float x2 = pos2.x;
		float z2 = pos2.z;
		return (float) Math.sqrt((x1-x2)*(x1-x2)+(z1-z2)*(z1-z2));
	}
}
