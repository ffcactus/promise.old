import React, { PropTypes } from 'react';
import CSSModules from 'react-css-modules';
import styles from '../../styles/serverDetailTable.css';

const ServerEthernetTable = props =>
  <table className={styles.root}>
    <thead className="thead">
      <tr className="tr">
        <th className="th"><p>Name</p></th>
        <th className="th">Link Status</th>
        <th className="th">Permanent MAC Address</th>
        <th className="th">IPv4 Addresses</th>
        <th className="th">IPv6 Addresses</th>
        <th className="th">IPv6DefaultGateway</th>
        <th className="th">VLANs</th>
      </tr>
    </thead>
    <tbody>
      {
        props.ethernetInterfaces.map(each => {
          return (
            <tr key={each.PermanentMACAddress}>
              <td>{each.Name}</td>
              <td>{each.LinkStatus}</td>
              <td>{each.PermanentMACAddress}</td>
              <td>
                {
                  each.IPv4Addresses.map(ipv4 => {
                    return (
                      <tr>
                        <tr>
                          <th>Address</th><td>{ipv4.Address}</td>
                        </tr>
                        <tr>
                          <th>Subnet Mask</th><td>{ipv4.SubnetMask}</td>
                        </tr>
                        <tr>
                          <th>Gateway</th><td>{ipv4.Gateway}</td>
                        </tr>
                      </tr>
                    );
                  })
                }
              </td>
              <td>
                {
                  each.IPv6Addresses.map(ipv6 => {
                    return (
                      <tr>
                        <tr>
                          <th>Address</th><td>{ipv6.Address}</td>
                        </tr>
                        <tr>
                          <th>Prefix Length</th><td>{ipv6.PrefixLength}</td>
                        </tr>
                      </tr>
                    );
                  })
                }
              </td>
              <td>{each.IPv6DefaultGateway}</td>
              <td>
                {
                  each.VLANs.map(vlan => {
                    return (
                      <tr>
                        <tr>
                          <th>Enabled</th><td>{vlan.VLANEnable}</td>
                        </tr>
                        <tr>
                          <th>Id</th><td>{vlan.VLANId}</td>
                        </tr>
                      </tr>
                    );
                  })
                }
              </td>
            </tr>
          );
        })
      }
    </tbody>
  </table>;


ServerEthernetTable.propTypes = {
  ethernetInterfaces: PropTypes.array,
  footer: PropTypes.object
};
export default CSSModules(ServerEthernetTable, styles);
