package com.andersen.banking.meeting_impl.mapper;

import com.andersen.banking.meeting_api.dto.BankBranchDto;
import com.andersen.banking.meeting_api.dto.BankBranchDto.BankBranchDtoBuilder;
import com.andersen.banking.meeting_db.entities.BankBranch;
import com.andersen.banking.meeting_db.entities.Street;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-17T15:16:13+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class BankBranchMapperImpl implements BankBranchMapper {

    @Override
    public BankBranchDto bankBranch2BankBranchDto(BankBranch bankBranch) {
        if ( bankBranch == null ) {
            return null;
        }

        BankBranchDtoBuilder bankBranchDto = BankBranchDto.builder();

        bankBranchDto.streetName( bankBranchStreetName( bankBranch ) );
        bankBranchDto.id( bankBranch.getId() );
        bankBranchDto.house( bankBranch.getHouse() );
        bankBranchDto.building( bankBranch.getBuilding() );
        bankBranchDto.branchNumber( bankBranch.getBranchNumber() );
        bankBranchDto.branchCoordinates( bankBranch.getBranchCoordinates() );
        bankBranchDto.workAtWeekend( bankBranch.isWorkAtWeekend() );
        bankBranchDto.cashWithdraw( bankBranch.isCashWithdraw() );
        bankBranchDto.moneyTransfer( bankBranch.isMoneyTransfer() );
        bankBranchDto.acceptPayment( bankBranch.isAcceptPayment() );
        bankBranchDto.currencyExchange( bankBranch.isCurrencyExchange() );
        bankBranchDto.exoticCurrency( bankBranch.isExoticCurrency() );
        bankBranchDto.ramp( bankBranch.isRamp() );
        bankBranchDto.replenishCard( bankBranch.isReplenishCard() );
        bankBranchDto.replenishAccount( bankBranch.isReplenishAccount() );
        bankBranchDto.consultation( bankBranch.isConsultation() );
        bankBranchDto.insurance( bankBranch.isInsurance() );
        bankBranchDto.closed( bankBranch.isClosed() );

        return bankBranchDto.build();
    }

    private String bankBranchStreetName(BankBranch bankBranch) {
        if ( bankBranch == null ) {
            return null;
        }
        Street street = bankBranch.getStreet();
        if ( street == null ) {
            return null;
        }
        String name = street.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
