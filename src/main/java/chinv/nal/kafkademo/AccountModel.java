package chinv.nal.kafkademo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class AccountModel implements Serializable
{
	private String accountId;
}
